package com.controller.shopadmin;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dto.EchartSeries;
import com.dto.EchartXAxis;
import com.dto.UserAccessToken;
import com.dto.UserProductMapExecution;
import com.dto.WechatInfo;
import com.enums.UserProductMapStateEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pojo.PersonInfo;
import com.pojo.Product;
import com.pojo.ProductSellDaily;
import com.pojo.Shop;
import com.pojo.UserProductMap;
import com.pojo.WechatAuth;
import com.service.PersonInfoService;
import com.service.ProductSellDailyService;
import com.service.ProductService;
import com.service.ShopService;
import com.service.UserProductMapService;
import com.service.WechatAuthService;
import com.util.HttpServletRequestUtil;
import com.util.wechat.WechatUtil;

@Controller
@RequestMapping("/shopadmin")
public class UserProductManagementController {
	@Autowired
	private UserProductMapService userProductMapService;

	@Autowired
	private ProductSellDailyService productSellDailyService;

	@Autowired
	private WechatAuthService wechatAuthService;

	@Autowired
	private ProductService productService;

	@Autowired
	private PersonInfoService personInfoService;

	@Autowired
	private ShopService shopService;

	@RequestMapping(value = "/listuserproductmapsbyshop", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> listUserProductMapsByShop(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		// 获取分页信息
		int pageIndex = HttpServletRequestUtil.getInt(request, "pageIndex");
		int pageSize = HttpServletRequestUtil.getInt(request, "pageSize");
		// 获取当前的店铺信息
		Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
		// 空值校验，主要确保shopId不为空
		if ((pageIndex > -1) && (pageSize > -1) && (currentShop != null) && (currentShop.getShopId() != null)) {
			// 添加查询条件
			UserProductMap userProductMapCondition = new UserProductMap();
			userProductMapCondition.setShop(currentShop);
			String productName = HttpServletRequestUtil.getString(request, "productName");
			if (productName != null) {
				// 若前端想按照商品名模糊查询，则传入productName
				Product product = new Product();
				product.setProductName(productName);
				userProductMapCondition.setProduct(product);
			}
			// 根据传入的查询条件获取该店铺的商品销售情况
			UserProductMapExecution ue = userProductMapService.listUserProductMap(userProductMapCondition, pageIndex,
					pageSize);
			modelMap.put("userProductMapList", ue.getUserProductMapList());
			modelMap.put("count", ue.getCount());
			modelMap.put("success", true);
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "empty pageSize or pageIndex or shopId");
		}
		return modelMap;
	}

	@RequestMapping(value = "/listproductselldailyinfobyshop", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> listProductSellDailyInfoByShop(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		// 获取当前的店铺信息
		Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
		// 空值校验，主要确保shopId不为空
		if ((currentShop != null) && (currentShop.getShopId() != null)) {
			// 添加查询条件
			ProductSellDaily productSellDailyCondition = new ProductSellDaily();
			productSellDailyCondition.setShop(currentShop);
			Calendar calendar = Calendar.getInstance();
			// 获取昨天的日期
			calendar.add(Calendar.DATE, -1);
			Date endTime = calendar.getTime();
			// 获取七天前的日期
			calendar.add(Calendar.DATE, -6);
			Date beginTime = calendar.getTime();
			// 根据传入的查询条件获取该店铺的商品销售情况
			List<ProductSellDaily> productSellDailyList = productSellDailyService
					.listProductSellDaily(productSellDailyCondition, beginTime, endTime);
			// 指定日期格式
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			// 商品名列表，保证唯一性
			HashSet<String> legendData = new HashSet<String>();
			// x轴数据
			HashSet<String> xData = new HashSet<String>();
			// 定义series
			List<EchartSeries> series = new ArrayList<EchartSeries>();
			// 日销量列表
			List<Integer> totalList = new ArrayList<Integer>();
			// 当前商品名，默认为空
			String currentProductName = "";
			for (int i = 0; i < productSellDailyList.size(); i++) {
				ProductSellDaily productSellDaily = productSellDailyList.get(i);
				// 自动去重
				legendData.add(productSellDaily.getProduct().getProductName());
				xData.add(sdf.format(productSellDaily.getCreateTime()));
				if (!currentProductName.equals(productSellDaily.getProduct().getProductName())
						&& !currentProductName.isEmpty()) {
					// 如果currentProductName不等于获取的商品名，或者已遍历到列表的末尾，且currentProductName
					// 则是遍历到下一个商品的日销量信息了，将前一轮遍历的信息放入series当中，
					// 包括了商品以及与商品对应的统计日期以及当日销量
					EchartSeries es = new EchartSeries();
					es.setName(currentProductName);
					es.setData(totalList.subList(0, totalList.size()));
					series.add(es);
					// 重置totalList
					totalList = new ArrayList<Integer>();
					// 变换下currentProductId为当前的productId
					currentProductName = productSellDaily.getProduct().getProductName();
					// 继续添加新的值
					totalList.add(productSellDaily.getTotal());
				} else {
					// 如果还是当前的productId则继续添加新值
					totalList.add(productSellDaily.getTotal());
					currentProductName = productSellDaily.getProduct().getProductName();
				}
				// 队列之末，需要将最后的一个商品销量信息也添加上
				if (i == productSellDailyList.size() - 1) {
					EchartSeries es = new EchartSeries();
					es.setName(currentProductName);
					es.setData(totalList.subList(0, totalList.size()));
					series.add(es);
				}
			}
			modelMap.put("series", series);
			modelMap.put("legendData", legendData);
			// 拼接出xAxis
			List<EchartXAxis> xAxis = new ArrayList<EchartXAxis>();
			EchartXAxis exa = new EchartXAxis();
			exa.setData(xData);
			xAxis.add(exa);
			modelMap.put("xAxis", xAxis);
			modelMap.put("success", true);
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "empty shopId");
		}
		return modelMap;
	}

	@RequestMapping(value = "/adduserproductmap", method = RequestMethod.GET)
	@ResponseBody
	private String addUserProductMap(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {
		// 获取微信授权信息
		WechatAuth auth = getOperatorInfo(request);
		if (auth != null) {
			PersonInfo operator = auth.getPersonInfo();
			request.getSession().setAttribute("user", operator);

			// 获取二维码里state携带的content信息并解码
			String qrCodeinfo = new String(
					URLDecoder.decode(HttpServletRequestUtil.getString(request, "state"), "UTF-8"));
			ObjectMapper mapper = new ObjectMapper();
			WechatInfo wechatInfo = null;
			try {
				// 将解码后的内容用aaa去替换掉之前生成二维码的时候加入的aaa前缀，转换成WechatInfo实体类
				wechatInfo = mapper.readValue(qrCodeinfo.replace("aaa", "\""), WechatInfo.class);
			} catch (Exception e) {
				return "shopadmin/operationfail";
			}
			// 校验二维码是否已经过期
			if (!checkQRCodeInfo(wechatInfo)) {
				return "shopadmin/operationfail";
			}
			// 获取添加消费记录所需要的参数并组件成userproductmap实例
			int productId = wechatInfo.getProductId();
			int customerId = wechatInfo.getCustomerId();
			UserProductMap userProductMap = compactUserProductMap4Add(customerId, productId, auth.getPersonInfo());
			// 空值校验
			if (userProductMap != null && customerId != -1) {
				try {
					// 判断当前用户是否有权利操作
					if (!checkShopAuth(operator.getUserId(), userProductMap)) {
						return "shopadmin/operationfail";
					}
					// 添加消费记录
					UserProductMapExecution se = userProductMapService.addUserProductMap(userProductMap);
					if (se.getState() == UserProductMapStateEnum.SUCCESS.getState()) {
						return "shopadmin/operationsuccess";
					}
				} catch (RuntimeException e) {
					return "shopadmin/operationfail";
				}
			}
		}
		return "shopadmin/operationfail";
	}

	/**
	 * 判断当前操作用户是否为店家本人，并且判断操作的店铺是否为店家本人的商铺
	 * 
	 * @param userId
	 * @param userProductMap
	 * @return
	 */
	private boolean checkShopAuth(int userId, UserProductMap userProductMap) {
		// 根据用户编号，获取用户信息
		PersonInfo personInfo = personInfoService.getPersonInfoById(userId);
		// 判断用户的类型是否为店家
		if (personInfo.getUserType() == 2) {
			// 判断当前的商品是否属于该店家的
			if (userProductMap != null && userProductMap.getShop().getShopId() != null) {
				// 根据店铺编号获取当前的店铺信息
				Shop shop = shopService.getByShopId(userProductMap.getShop().getShopId());
				// 获取当前店铺的店家编号
				int nowOwnerId = shop.getOwnerId();
				if (nowOwnerId == personInfo.getUserId()) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	/**
	 * 根据二维码携带的createTime判断其是否超过了10分钟，超过十分钟则认为过期
	 * 
	 * @param wechatInfo
	 * @return
	 */
	private boolean checkQRCodeInfo(WechatInfo wechatInfo) {
		if (wechatInfo != null && wechatInfo.getProductId() > 0 && wechatInfo.getCustomerId() > 0
				&& wechatInfo.getCreateTime() != null) {
			long nowTime = System.currentTimeMillis();
			if ((nowTime - wechatInfo.getCreateTime() <= 600000)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	/**
	 * 根据传入的customerId，productId以及操作员信息组件用户消费记录
	 * 
	 * @param customerId
	 * @param productId
	 * @param personInfo
	 * @return
	 */
	private UserProductMap compactUserProductMap4Add(int customerId, int productId, PersonInfo personInfo) {
		UserProductMap userProductMap = null;
		if (customerId > 0 && productId > 0) {
			userProductMap = new UserProductMap();
			PersonInfo customer = new PersonInfo();
			customer.setUserId(customerId);
			Product product = productService.getProductById(productId);
			userProductMap.setProduct(product);
			userProductMap.setShop(product.getShop());
			userProductMap.setUser(customer);
			userProductMap.setCreateTime(new Date());
		}
		return userProductMap;
	}

	/**
	 * 根据code获取UserAccessToken，进而通过token里的openId获取微信用户信息
	 * 
	 * @param request
	 * @return
	 */
	private WechatAuth getOperatorInfo(HttpServletRequest request) {
		String code = request.getParameter("code");
		WechatAuth auth = null;
		if (null != code) {
			UserAccessToken token;
			try {
				token = WechatUtil.getUserAccessToken(code);
				String openId = token.getOpenId();
				request.getSession().setAttribute("openId", openId);
				auth = wechatAuthService.getWechatAuthByOpenId(openId);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return auth;
	}

}
