package com.controller.frontend;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.pojo.PersonInfo;
import com.pojo.Product;
import com.service.ProductService;
import com.util.CodeUtil;
import com.util.HttpServletRequestUtil;
import com.util.baidu.ShortNetAddressUtil;

@Controller
@RequestMapping("/frontend")
public class ProductDetailController {
	@Autowired
	private ProductService productService;
	// http://localhost:8080/o2o/shopadmin/adduserproductmap
	private static String URLPREFIX = "https://open.weixin.qq.com/connect/oauth2/authorize?"
			+ "appid=wxaf8bd3909de93819&" + "redirect_uri=https://www.yyzheng.cn/o2o/shopadmin/adduserproductmap&"
			+ "response_type=code&scope=snsapi_userinfo&state=";
	private static String URLSUFFIX = "#wechat_redirect";

	@RequestMapping(value = "/listproductdetailpageinfo", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> listProductDetailPageInfo(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		int productId = HttpServletRequestUtil.getInt(request, "productId");
		Product product = null;
		if (productId != -1) {
			// 根据productId获取商品信息，包含商品详情图列表
			product = productService.getProductById(productId);
			// 2.0新增
			PersonInfo user = (PersonInfo) request.getSession().getAttribute("user");
			if (user == null) {
				modelMap.put("needQRCode", false);
			} else {
				modelMap.put("needQRCode", true);
			}
			modelMap.put("product", product);
			modelMap.put("success", true);
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "empty productId");
		}
		return modelMap;
	}

	@RequestMapping(value = "/generateqrcode4product", method = RequestMethod.GET)
	@ResponseBody
	private void generateQRCode4Product(HttpServletRequest request, HttpServletResponse response) {
		// 获取前端传递过来的商品Id
		int productId = HttpServletRequestUtil.getInt(request, "productId");
		// 从session里获取当前顾客的信息
		PersonInfo user = (PersonInfo) request.getSession().getAttribute("user");
		// 空值判断
		if (productId != -1 && user != null && user.getUserId() != null) {
			// 获取当前时间戳，以保证二维码的时间有效性，精确到毫秒
			long timpStamp = System.currentTimeMillis();
			// 将商品id，顾客id和timpStamp传入content，附值到state中，这样微信获取到这些信息后会回传到用户商品映射信息的添加方法里
			// 加上aaa是为了一会的添加信息的方法里替换这些信息使用
			String content = "{aaaproductIdaaa:" + productId + ",aaacustomerIdaaa:" + user.getUserId()
					+ ",aaacreateTimeaaa:" + timpStamp + "}";
			try {
				// 将content的信息先进行base64编码以避免特殊字符造成的干扰，之后拼接目标URL
				String longUrl = URLPREFIX + URLEncoder.encode(content, "UTF-8") + URLSUFFIX;
				// 将目标URL转换成端的URL
				String shortUrl = ShortNetAddressUtil.getShortURL(longUrl);
				// 调用二维码生成的工具类方法，传入短的URL，生成二维码
				BitMatrix qRcodeImg = CodeUtil.generateQRCodeStream(shortUrl, response);
				// 将二维码以图片的流的形式输入到前端
				MatrixToImageWriter.writeToStream(qRcodeImg, "png", response.getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
