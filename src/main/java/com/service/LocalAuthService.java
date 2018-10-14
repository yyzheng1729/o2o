package com.service;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.dto.LocalAuthExecution;
import com.pojo.LocalAuth;

public interface LocalAuthService {

	/**
	 * 通过账号和密码获取平台账号信息
	 * 
	 * @param userName
	 * @return
	 */
	LocalAuth getLocalAuthByUserNameAndPwd(String username, String password);

	/**
	 * 通过userId获取平台账号信息
	 * 
	 * @param userId
	 * @return
	 */
	LocalAuth getLocalAuthByUserId(int userId);

	/**
	 * 绑定微信，生成平台专属的账号
	 * 
	 * @param localAuth
	 * @return
	 * @throws RuntimeException
	 */
	LocalAuthExecution bindLocalAuth(LocalAuth localAuth) throws RuntimeException;

	/**
	 * 修改平台账号的登录密码
	 * 
	 * @param localAuthId
	 * @param userName
	 * @param password
	 * @param newPassword
	 * @param lastEditTime
	 * @return
	 */
	LocalAuthExecution modifyLocalAuth(int userId, String username, String password, String newPassword);
}
