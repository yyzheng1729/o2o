package com.service;

import java.util.List;

import com.dto.ProductCategoryExecution;
import com.pojo.ProductCategory;

public interface ProductCategoryService {
	/**
	 * 查询指定某个店铺下的所有商品类别信息
	 * 
	 * @param int
	 *            shopId
	 * @return List<ProductCategory>
	 */
	List<ProductCategory> getProductCategoryList(int shopId);

	/**
	 * 批量添加商品类别
	 * 
	 * @param productCategory
	 * @return
	 * @throws RuntimeException
	 */
	ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategoryList) throws RuntimeException;

	/**
	 * 将此类别下的商品里的类别id置为空，再删除掉该商品类别
	 * 
	 * @param productCategoryId
	 * @param shopId
	 * @return
	 */
	ProductCategoryExecution deleteProductCategory(int productCategoryId, int shopId) throws RuntimeException;
}
