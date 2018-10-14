package com.controller.frontend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/frontend")
public class FrontendController {

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	private String index() {
		// 转发到前段展示系统首页
		return "frontend/index";
	}

	@RequestMapping(value = "/shoplist", method = RequestMethod.GET)
	private String showShopList() {
		// 转发到商店列表页
		return "frontend/shoplist";
	}

	@RequestMapping(value = "/shopdetail", method = RequestMethod.GET)
	private String showShopDetail() {
		// 转发到商店详情页
		return "frontend/shopdetail";
	}
	
	@RequestMapping(value = "/productdetail", method = RequestMethod.GET)
	private String showProductdetail() {
		// 转发到商店详情页
		return "frontend/productdetail";
	}
	
	@RequestMapping(value = "/myrecord", method = RequestMethod.GET)
	private String myrecord() {
		// 转发到消费记录列表页
		return "frontend/myrecord";
	}
}
