package com.dao;

import java.util.List;

import com.pojo.ProductImg;

public interface ProductImgDao {

	/**
	 * 根据商品ID查询商品的所有详情图
	 * 
	 * @param productId
	 * @return
	 */
	List<ProductImg> queryProductImgList(int productId);

	/**
	 * 批量添加商品详情图片
	 * 
	 * @param productImgList
	 * @return
	 */
	int batchInsertProductImg(List<ProductImg> productImgList);

	/**
	 * 删除指定商品下的所有详情图
	 * 
	 * @param productId
	 * @return
	 */
	int deleteProductImgByProductId(int productId);
}
