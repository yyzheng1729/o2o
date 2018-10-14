package com.controller.frontend;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dto.ProductExecution;
import com.pojo.Product;
import com.pojo.ProductCategory;
import com.pojo.Shop;
import com.pojo.ShopCategory;
import com.service.ProductCategoryService;
import com.service.ProductService;
import com.service.ShopService;
import com.util.HttpServletRequestUtil;

@Controller
@RequestMapping("/frontend")
public class ShopDetailController {
	@Autowired
	private ShopService shopService;
	@Autowired
	private ProductService productService;
	@Autowired
	private ProductCategoryService productCategoryService;

	/**
	 * 获取店铺信息以及该店铺下面的商品类别列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/listshopdetailpageinfo", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> listShopDetailPageInfo(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		// 获取前台传过来的shopId
		int shopId = HttpServletRequestUtil.getInt(request, "shopId");
		Shop shop = null;
		List<ProductCategory> productCategoryList = null;
		if (shopId != -1) {
			// 获取店铺id为shopId的店铺信息
			shop = shopService.getByShopId(shopId);
			// 获取店铺下面的商品类别列表
			productCategoryList = productCategoryService.getProductCategoryList(shopId);
			modelMap.put("shop", shop);
			modelMap.put("productCategoryList", productCategoryList);
			modelMap.put("success", true);
		} else {
			modelMap.put("success", false);
			modelMap.put("success", "empty shopId");
		}
		return modelMap;
	}

	/**
	 * 依据查询条件分页列出该店铺下面的所有商品
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/listproductsbyshop", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> listProductsByShop(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		// 获取页码
		int pageIndex = HttpServletRequestUtil.getInt(request, "pageIndex");
		// 获取一页需要显示的条数
		int pageSize = HttpServletRequestUtil.getInt(request, "pageSize");
		// 获取店铺ID
		int shopId = HttpServletRequestUtil.getInt(request, "shopId");
		// 非空判断
		if ((pageIndex > -1) && (pageSize > -1) && (shopId > -1)) {
			// 试着获取商品类别Id
			int productCategoryId = HttpServletRequestUtil.getInt(request, "productCategoryId");
			// 尝试获取模糊查找的商品名字
			String productName = HttpServletRequestUtil.getString(request, "productName");
			// 组合查询
			Product productCondition = compactProductCondition4Search(shopId, productCategoryId, productName);
			// 按照传入的查询条件以及分页信息返回相应商品列表以及总数
			ProductExecution pe = productService.getProductList(productCondition, pageIndex, pageSize);
			modelMap.put("productList", pe.getProductList());
			modelMap.put("count", pe.getCount());
			modelMap.put("success", true);
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "empty pageSize or pageIndex or shopId");
		}
		return modelMap;
	}

	/**
	 * 组合查询条件，并将条件封装到productCondition对象里返回
	 * 
	 * @param shopId
	 * @param productCategoryId
	 * @param productName
	 * @return
	 */
	private Product compactProductCondition4Search(int shopId, int productCategoryId, String productName) {
		Product productCondition = new Product();
		Shop shop = new Shop();
		shop.setShopId(shopId);
		productCondition.setShop(shop);
		if (productCategoryId != -1) {
			// 查询某个商品类别下面到商品列表
			ProductCategory productCategory = new ProductCategory();
			productCategory.setProductCategoryId(productCategoryId);
			productCondition.setProductCategory(productCategory);
		}
		if (productName != null) {
			// 查询名字里包含productName到店铺列表
			productCondition.setProductName(productName);
		}
		// 只允许选出状态为上架到商品
		productCondition.setEnableStatus(1);
		return productCondition;
	}
}
