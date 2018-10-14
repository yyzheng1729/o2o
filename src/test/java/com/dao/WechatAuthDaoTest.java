package com.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.pojo.PersonInfo;
import com.pojo.WechatAuth;

public class WechatAuthDaoTest extends BaseTest {
	@Autowired
	private WechatAuthDao wechatAuthDao;

	@Test
	@Ignore
	public void testAInsertWechatAuth() throws Exception {
		// 新增一条微信账号
		WechatAuth wechatAuth = new WechatAuth();
		wechatAuth.setOpenId("dafahizhfdhai");
		wechatAuth.setUserId(2);
		wechatAuth.setCreateTime(new Date());
		int effectedNum = wechatAuthDao.insertWechatAuth(wechatAuth);
		assertEquals(1, effectedNum);
	}

	@Test
//	@Ignore
	public void testBQueryWechatAuthByOpenId() throws Exception {
		WechatAuth wechatAuth = wechatAuthDao.queryWechatInfoByOpenId("dafahizhfdhai");
		assertEquals("小小", wechatAuth.getPersonInfo().getName());
	}
}
