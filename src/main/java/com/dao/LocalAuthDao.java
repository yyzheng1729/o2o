package com.dao;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import com.pojo.LocalAuth;

public interface LocalAuthDao {

	/**
	 * 通过账号和密码查询对应信息，登录用
	 * 
	 * @param userName
	 * @param password
	 * @return
	 */
	LocalAuth queryLocalByUserNameAndPwd(@Param("username") String username, @Param("password") String password);

	/**
	 * 通过用户Id查询对应的localauth
	 * 
	 * @param userId
	 * @return
	 */
	LocalAuth queryLocalByUserId(@Param("userId") int userId);

	/**
	 * 添加平台账号
	 * 
	 * @param localAuth
	 * @return
	 */
	int insertLocalAuth(LocalAuth localAuth);

	/**
	 * 通过userId,username,password更改密码
	 * 
	 * @param localAuth
	 * @return
	 */
	int updateLocalAuth(@Param("userId") int userId, @Param("username") String username,
			@Param("password") String password, @Param("newPassword") String newPassword,
			@Param("lastEditTime") Date lastEditTime);
}
