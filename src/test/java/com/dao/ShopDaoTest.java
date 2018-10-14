package com.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.pojo.Area;
import com.pojo.PersonInfo;
import com.pojo.Shop;
import com.pojo.ShopCategory;

public class ShopDaoTest extends BaseTest{
	@Autowired
	private ShopDao shopDao;
	
	@Test
	@Ignore
	public void testInsertShop(){
		Shop shop = new Shop();
		PersonInfo owner = new PersonInfo();
		Area area = new Area();
		ShopCategory shopCategory = new ShopCategory();
		owner.setUserId(1);
		area.setAreaId(1);
		shopCategory.setShopCategoryId(1);
		shop.setOwner(owner);
		shop.setArea(area);
		shop.setShopCategory(shopCategory);
		shop.setShopName("测试店铺");
		shop.setShopDesc("test");
		shop.setShopAddr("test");
		shop.setPhone("15363386253");
		shop.setShopImg("test");
		shop.setCreateTime(new Date());
		shop.setEnableStatus(1);
		shop.setAdvice("审核中");
		int effectedNum = shopDao.insertShop(shop);
		assertEquals(1,effectedNum);
	}
	
	@Test
	@Ignore
	public void testUpdateShop(){
		Shop shop = new Shop();
		shop.setShopId(1);
		shop.setShopDesc("测试描述");
		shop.setShopAddr("测试地址");
		shop.setLastEditTime(new Date());
		int effectedNum = shopDao.updateShop(shop);
		assertEquals(1,effectedNum);
	}
	
	@Test
	@Ignore
	public void testQueryByShopId(){
		int shopId = 1;
		Shop shop = shopDao.queryByShopId(shopId);
		System.out.println("areaId:"+shop.getArea().getAreaId());
		System.out.println("areaName:"+shop.getArea().getAreaName());
	}
	
	@Test
	public void testQueryShopList() {
		Shop shopCondition = new Shop();
		PersonInfo owner = new PersonInfo();
		owner.setUserId(1);
		shopCondition.setOwner(owner);
		List<Shop> shopList = shopDao.queryShopList(shopCondition, 0, 5);
		int count = shopDao.queryShopCount(shopCondition);
		System.out.println("大小 == >:"+shopList.size());
		System.out.println("总数 == >:"+count);
		ShopCategory sc = new ShopCategory();
		sc.setShopCategoryId(9);
		shopCondition.setShopCategory(sc);
		shopList = shopDao.queryShopList(shopCondition, 0, 4);
		count = shopDao.queryShopCount(shopCondition);
		System.out.println("大小 == >:"+shopList.size());
		System.out.println("总数 == >:"+count);
		
//		Shop shopCondition = new Shop();
//		ShopCategory shopCategory = new ShopCategory();
//		shopCategory.setParentId(1);
//		shopCondition.setShopCategory(shopCategory);
//		List<Shop> shopList = shopDao.queryShopList(shopCondition, 0, 5);
//		int count = shopDao.queryShopCount(shopCondition);
//		System.out.println("大小 == >:"+shopList.size());
//		System.out.println("总数 == >:"+count);
	}
}
