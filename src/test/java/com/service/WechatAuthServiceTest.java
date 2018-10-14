package com.service;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.BaseTest;
import com.dto.WechatAuthExecution;
import com.enums.WechatAuthStateEnum;
import com.pojo.PersonInfo;
import com.pojo.WechatAuth;

@Service
public class WechatAuthServiceTest extends BaseTest{
	@Autowired
	private WechatAuthService wechatAuthService;
	
	@Test
	public void testRegister(){
		//新增一条微信账号
		WechatAuth wechatAutch = new WechatAuth();
		PersonInfo personInfo = new PersonInfo();
		String openId = "dafahizhfdhaih";
		//给微信账号设置上用户信息，但不设置上用户Id
		//希望创建微信账号的时候自动创建用户信息
		personInfo.setCreateTime(new Date());
		personInfo.setName("测试一下");
		personInfo.setUserType(1);
		wechatAutch.setPersonInfo(personInfo);
		wechatAutch.setOpenId(openId);
		wechatAutch.setCreateTime(new Date());
		WechatAuthExecution wae = wechatAuthService.register(wechatAutch);
		assertEquals(WechatAuthStateEnum.SUCCESS.getState(),wae.getState());
		//通过openId找到新增的wechatAuth
		wechatAutch = wechatAuthService.getWechatAuthByOpenId(openId);
		//打印用户名字看看跟预期是否相符
		System.out.println(wechatAutch.getPersonInfo().getName());
	}
}
