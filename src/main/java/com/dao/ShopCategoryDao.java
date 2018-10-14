package com.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pojo.ShopCategory;

public interface ShopCategoryDao {
	
	List<ShopCategory> queryShopCategory(@Param("shopCategoryCondition")ShopCategory shopCategoryCondition);
}
