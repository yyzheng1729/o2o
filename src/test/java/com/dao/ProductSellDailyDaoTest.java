package com.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.pojo.ProductSellDaily;
import com.pojo.Shop;

public class ProductSellDailyDaoTest extends BaseTest {
	@Autowired
	private ProductSellDailyDao productSellDailyDao;

	/**
	 * 测试添加功能
	 */
	@Test
	@Ignore
	public void testInsertProductSellDaily() {
		// 创建商品日销量统计
		int effectdNum = productSellDailyDao.insertProductSellDaily();
		assertEquals(3, effectdNum);
	}
	
	/**
	 * 测试添加功能
	 * 
	 */
	@Test
	@Ignore
	public void testInsertDefaultProductSellDaily(){
		//创建商品日销量统计
		int effectedNum = productSellDailyDao.insertDefaultProductSellDaily();
//		assertEquals(10,effectedNum);
	}
	
	/**
	 * 测试查询功能
	 */
	@Test
	@Ignore
	public void testQueryproductSellDaily() {
		ProductSellDaily productSellDaily = new ProductSellDaily();
		//叠加店铺去查询
		Shop shop = new Shop();
		shop.setShopId(13);
		productSellDaily.setShop(shop);
		List<ProductSellDaily> productSellDailyList = productSellDailyDao.queryProductSellDailyList(productSellDaily, null, null);
		assertEquals(3, productSellDailyList.size());
	}
	
	
}
