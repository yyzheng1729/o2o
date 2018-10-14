package com.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.pojo.PersonInfo;
import com.pojo.Product;
import com.pojo.Shop;
import com.pojo.UserProductMap;



public class UserproductMapDaoTest extends BaseTest {
	@Autowired
	private UserProductMapDao userProductMapDao;
	
	@Test
	@Ignore
	public void testInsertUserProductMap() throws Exception {
		UserProductMap userProductMap = new UserProductMap();
		PersonInfo personInfo = new PersonInfo();
		Product product = new Product();
		Shop shop = new Shop();
		personInfo.setUserId(5);
		product.setProductId(4);
		shop.setShopId(13);
		userProductMap.setCreateTime(new Date());
		userProductMap.setUser(personInfo);
		userProductMap.setShop(shop);
		userProductMap.setProduct(product);
		int effectNum = userProductMapDao.insertUserProductMap(userProductMap);
	}
	
	@Test
	public void testQueryUserProductMap() throws Exception{
		UserProductMap userProductMap = new UserProductMap();
		PersonInfo user = new PersonInfo();
		Product product = new Product();
		Shop shop = new Shop();
		//按顾客名字模糊查询
		user.setName("测试");
		userProductMap.setUser(user);
		List<UserProductMap> userProductMapList = userProductMapDao.queryUserProductMapList(userProductMap, 0, 5);
		assertEquals(2,userProductMapList.size());
		int count = userProductMapDao.queryUserProductMapCount(userProductMap);
		assertEquals(3,count);
		System.out.println(count);
	}
}
