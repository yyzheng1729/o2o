package com.service;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.BaseTest;
import com.dto.LocalAuthExecution;
import com.enums.WechatAuthStateEnum;
import com.pojo.LocalAuth;
import com.pojo.PersonInfo;

@Service
public class LocalAuthServiceTest extends BaseTest{
	@Autowired
	private LocalAuthService localAuthService;
	
	@Test
	@Ignore
	public void testBindLocalAuth() {
		//新增一条平台账号
		LocalAuth localAuth = new LocalAuth();
		PersonInfo personInfo = new PersonInfo();
		String username = "testusername1";
		String password = "testpassword1";
		//给平台账号设置上用户信息
		//给用户设置上用户Id，标明是某个用户创建的账号
		personInfo.setUserId(1);
		//给平台账号设置用户信息，标明是与哪个用户绑定
		localAuth.setPersonInfo(personInfo);
		//设置账号
		localAuth.setUsername(username);
		//设置密码
		localAuth.setPassword(password);
		//绑定账号
		LocalAuthExecution lae = localAuthService.bindLocalAuth(localAuth);
		System.out.println("WechatAuthStateEnum.SUCCESS:"+WechatAuthStateEnum.SUCCESS.getState());
		System.out.println("lae.getState():"+lae.getState());
		assertEquals(WechatAuthStateEnum.SUCCESS.getState(),lae.getState());
		//通过userId找到新增的localAuth
		localAuth = localAuthService.getLocalAuthByUserId(personInfo.getUserId());
		//打印用户名字和账号密码看看跟预期是否相符
		System.out.println(localAuth.getPersonInfo().getName());
		System.out.println(localAuth.getPassword());
	}
	
	@Test
	public void testModifyLocalAuth(){
		//设置账号信息
		int userId = 1;
		String username = "testusername1";
		String password = "testpassword1";
		String newPassword = "dong";
		//修改该账号对应的密码
		LocalAuthExecution lae = localAuthService.modifyLocalAuth(userId, username, password, newPassword);
		assertEquals(WechatAuthStateEnum.SUCCESS.getState(),lae.getState());
		//通过账号密码找到修改后的localAuth
		LocalAuth localAuth = localAuthService.getLocalAuthByUserNameAndPwd(username, newPassword);
		//打印用户名字看看跟预期是否相符
		System.out.println(localAuth.getPersonInfo().getName());
	}
}
