package com.service;

import com.dto.WechatAuthExecution;
import com.pojo.WechatAuth;

public interface WechatAuthService {
	/**
	 * 通过openId查找平台对应的微信账号
	 * 
	 * @param openId
	 * @return
	 */
	WechatAuth getWechatAuthByOpenId(String openId);
	
	/**
	 * 注册本平台的微信账号
	 * 
	 * @param wechatAuth
	 * @return
	 * @throws RuntimeException
	 */
	WechatAuthExecution register(WechatAuth wechatAuth)throws RuntimeException;
}
