package com.controller.shopadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/shopadmin",method=(RequestMethod.GET))
public class ShopAdminController {
	
	@RequestMapping(value="/shopoperation")
	private String shopOperation(){
		//转发至店铺注册/编辑页面
		return "shop/shopoperation";
	}
	
	@RequestMapping(value="/shoplist")
	private String shopList(){
		//转发至店铺列表页面
		return "shop/shoplist";
	}

	@RequestMapping(value="/shopmanagement")
	private String shopManagement(){
		//转发至店铺管理页面
		return "shop/shopmanagement";
	}
	
	@RequestMapping(value="/productcategorymanagement")
	private String productCategoryManagement(){
		//转发至商品类别管理页面
		return "shop/productcategorymanagement";
	}
	
	@RequestMapping(value="/productoperation")
	public String productOperation(){
		//转发至商品添加/编辑页面
		return "shop/productoperation";
	}
	
	@RequestMapping(value="/productmanagement")
	public String productManagement(){
		//转发至商品管理页面
		return "shop/productmanagement";
	}
	
	@RequestMapping(value="/productbuycheck")
	public String productbuycheck(){
		//转发至消费记录页面
		return "shop/productbuycheck";
	}
	
	@RequestMapping(value="/operationfail")
	public String operationfail(){
		//转发到操作失败页面
		return "shop/operationfail";
	}

}
