package com.service;

import java.util.List;

import com.dto.ImageHolder;
import com.dto.ProductExecution;
import com.pojo.Product;

public interface ProductService {
	/**
	 * 添加商品信息以及图片处理
	 * 
	 * @param product
	 * @param thumbnail
	 * @param thumbnailName
	 * @param productImgList
	 * @param productImgNameList
	 * @return
	 * @throws RuntimeException
	 */
	ProductExecution addProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgList)
			throws RuntimeException;

	/**
	 * 通过商品Id查询唯一的商品信息
	 * 
	 * @param productId
	 * @return
	 */
	Product getProductById(int productId);

	/**
	 * 修改商品信息以及图片处理
	 * 
	 * @param product
	 * @param thumbnail
	 * @param productImgHolderList
	 * @return
	 * @throws RuntimeException
	 */
	ProductExecution modifyProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgHolderList)
			throws RuntimeException;
	
	/**
	 * 查询商品列表并分页，可输入的条件有：商品名（模糊），商品状态，店铺Id，商品类别
	 * 
	 * @param productCondition
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	ProductExecution getProductList(Product productCondition, int pageIndex, int pageSize);
}
