package com.service;

import java.util.List;

import com.pojo.ShopCategory;

public interface ShopCategoryService {
	
	/**
	 * 根据查询条件获取ShopCategory列表
	 * 
	 * @param shopCategoryCondition
	 * @return
	 */
	List<ShopCategory> getShopCategory(ShopCategory shopCategoryCondition);
}
