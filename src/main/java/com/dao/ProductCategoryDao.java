package com.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pojo.ProductCategory;

public interface ProductCategoryDao {

	/**
	 * 通過 shop id 查询店铺商品类别
	 * 
	 * @param shopId
	 * @return List<ProductCategory>
	 */
	List<ProductCategory> queryProductCategoryList(int shopId);

	/**
	 * 批量新增商品类别
	 * 
	 * @param productCategoryList
	 * @return
	 */
	int batchInsertProductCategory(List<ProductCategory> productCategoryList);

	/**
	 * 删除指定商品类别
	 * 
	 * @param productCategoryId
	 * @return
	 */
	int deleteProductCategory(@Param("productCategoryId")int productCategoryId,@Param("shopId")int shopId);
}
