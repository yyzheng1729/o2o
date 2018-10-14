package com.service;


import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.BaseTest;
import com.dto.ImageHolder;
import com.dto.ShopExecution;
import com.enums.ShopStateEnum;
import com.pojo.Area;
import com.pojo.PersonInfo;
import com.pojo.Shop;
import com.pojo.ShopCategory;

@Service
public class ShopServiceTest extends BaseTest{
	@Autowired
	private ShopService shopService;
	
	@Test
	@Ignore
	public void testModifyShop() throws RuntimeException,FileNotFoundException{
		Shop shop = shopService.getByShopId(2);
		shop.setShopId(1);
		shop.setShopName("同济饭店");
		shop.setShopDesc("外卖饭盒");
		File shopImg = new File("C:/Users/yy/Desktop/2015118050.jpg");
		InputStream is = new FileInputStream(shopImg);
		ImageHolder imageHolder = new ImageHolder( shopImg.getName(),is);
		ShopExecution shopExecution = shopService.modifyShop(shop, imageHolder);
		System.out.println("新的图片地址为："+ shopExecution.getShop().getShopImg());
	}
	
	@Test
	@Ignore
	public void testAddShop() throws FileNotFoundException{
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
		shop.setShopName("测试店铺dong");
		shop.setShopDesc("test1");
		shop.setShopAddr("test1");
		shop.setPhone("15363386253");
		shop.setCreateTime(new Date());
		shop.setEnableStatus(ShopStateEnum.CHECK.getState());
		shop.setAdvice("审核中");
		File shopImg = new File("C:/Users/yy/Desktop/2015118050.jpg");
		InputStream is = new FileInputStream(shopImg);
		ImageHolder imageHolder = new ImageHolder( shopImg.getName(),is);
		ShopExecution se = shopService.addShop(shop,imageHolder);
		assertEquals(ShopStateEnum.CHECK.getState(),se.getState());
	}
	
	@Test
	public void testGetShopList(){
		Shop shopCondition = new Shop();
		ShopCategory sc = new ShopCategory();
		sc.setShopCategoryId(1);
		shopCondition.setShopCategory(sc);
		ShopExecution se = shopService.getShopList(shopCondition, 3, 2);
		System.out.println("店铺列表数目=>"+se.getShopList().size());
		System.out.println("店铺总数为=>"+se.getCount());
	}
}
