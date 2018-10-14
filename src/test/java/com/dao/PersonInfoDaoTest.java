package com.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.pojo.PersonInfo;

public class PersonInfoDaoTest extends BaseTest {
	@Autowired
	private PersonInfoDao personInfoDao;

	@Test
//	@Ignore
	public void testAInsertPersonInfo() throws Exception {
		//设置新增的用户信息
		PersonInfo personInfo = new PersonInfo();
		personInfo.setName("美美");
		personInfo.setGender("女");
		personInfo.setUserType(1);
		personInfo.setCreateTime(new Date());
		personInfo.setLastEditTime(new Date());
		personInfo.setEnableStatus(1);
		int effectedNum = personInfoDao.insertPersonInfo(personInfo);
		assertEquals(1, effectedNum);
	}
	
	@Test
	@Ignore
	public void testBQueryPersonInfoById(){
		int userId = 1;
		PersonInfo person = personInfoDao.queryPersonInfoById(userId);
		System.out.println(person.getName());
	}
}
