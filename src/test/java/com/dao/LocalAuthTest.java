package com.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.pojo.LocalAuth;
import com.pojo.PersonInfo;
import com.util.MD5;

public class LocalAuthTest extends BaseTest{
	@Autowired
	private LocalAuthDao localAuthDao;
	
	private static final String username = "testusername";
	private static final String password = "testpassword";
	
	@Test
	@Ignore
	public void testInsertLocalAuth() throws Exception{
		//新增一条平台账号信息
		LocalAuth localAuth = new LocalAuth();
		PersonInfo personInfo = new PersonInfo();
		personInfo.setUserId(1);
		//给平台账号绑定上用户信息
		localAuth.setPersonInfo(personInfo);
		//设置上用户名和密码
		localAuth.setUsername(username);
		localAuth.setPassword(MD5.getMd5(password));
		localAuth.setCreateTime(new Date());
		int effectedNum = localAuthDao.insertLocalAuth(localAuth);
		assertEquals(1,effectedNum);
	}
	
	@Test
	@Ignore
	public void testQueryLocalByUserNameAndPwd()throws Exception{
		//按照账号和密码查询用户信息
		LocalAuth localAuth = localAuthDao.queryLocalByUserNameAndPwd(username, password);
		assertEquals("东东",localAuth.getPersonInfo().getName());
		System.out.println(localAuth.getPersonInfo().getName());
	}
	
	@Test
	@Ignore
	public void testQueryLocalByUserId(){
		//按照用户id查询平台账号，进而获取用户信息
		LocalAuth localAuth = localAuthDao.queryLocalByUserId(1);
		assertEquals("东东",localAuth.getPersonInfo().getName());
		System.out.println(localAuth.getPersonInfo().getName());
	}
	
	@Test
	@Ignore
	public void testUpdateLocalAuth(){
		//依据用户Id,平台账号，以及旧密码修改平台账号密码
		Date now = new Date();
		String newPassword = "dong";
		int effectedNum = localAuthDao.updateLocalAuth(1, username, password, newPassword, now);
		assertEquals(1,effectedNum);
		//查询出该条平台账号的最新信息
		LocalAuth localAuth = localAuthDao.queryLocalByUserId(1);
		//输出新密码
		System.out.println(localAuth.getPassword());
	}
}
 