package com.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.pojo.ShopCategory;

public class ShopCategoryDaoTest extends BaseTest{
	@Autowired
	private ShopCategoryDao shopCategoryDao;
	
	@Test
	public void testQueryShopCategory(){
//		ShopCategory sc = new ShopCategory();
//		sc.setParentId(1);
		List<ShopCategory> shopCategoryList = shopCategoryDao.queryShopCategory(null);
//		assertEquals(2,shopCategoryList.size());
		System.out.println(shopCategoryList.size());
	}
}
