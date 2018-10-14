package com.dao;

import com.pojo.PersonInfo;

public interface PersonInfoDao {

	/**
	 * 通过用户Id查询用户
	 * 
	 * @param userId
	 * @return
	 */
	PersonInfo queryPersonInfoById(int userId);

	/**
	 * 添加用户信息
	 * 
	 * @param wechatAuth
	 * @return
	 */
	int insertPersonInfo(PersonInfo personInfo);
}
