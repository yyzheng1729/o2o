package com.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import com.pojo.ProductCategory;

public class ProductCategoryDaoTest extends BaseTest{
	@Autowired
	ProductCategoryDao productCategoryDao;
	
	@Test
	@Ignore
	public void testQueryByShopId() throws Exception {
		int shopId = 1;
		List<ProductCategory> productCatogoryList = productCategoryDao.queryProductCategoryList(shopId);
		System.out.println("条数："+ productCatogoryList.size());
	}
	
	@Test
	@Ignore
	public void testBatchInsertProductCategory(){
		ProductCategory productCategory = new ProductCategory();
		productCategory.setProductCategoryName("鸡排饭");
		productCategory.setPriority(1);
		productCategory.setCreateTime(new Date());
		productCategory.setShopId(3);
		ProductCategory productCategory2 = new ProductCategory();
		productCategory2.setProductCategoryName("炸鸡+奶茶");
		productCategory2.setPriority(2);
		productCategory2.setCreateTime(new Date());
		productCategory2.setShopId(3);
		List<ProductCategory> productCategoryList = new ArrayList<ProductCategory>();
		productCategoryList.add(productCategory);
		productCategoryList.add(productCategory2);
		int effectedNum = productCategoryDao.batchInsertProductCategory(productCategoryList);
		assertEquals(2,effectedNum);
	}
	
	@Test
	public void testDeleteProductCategory(){
		int shopId = 3;
		List<ProductCategory> productCategoryList = productCategoryDao.queryProductCategoryList(shopId);
		for(ProductCategory pc:productCategoryList){
			if("炸鸡+奶茶".equals(pc.getProductCategoryName())){
				int effectedNum = productCategoryDao.deleteProductCategory(pc.getProductCategoryId(), shopId);
				assertEquals(1,effectedNum);
			}
		}
	}
}
