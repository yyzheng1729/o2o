package com.service;

import com.pojo.PersonInfo;

public interface PersonInfoService {

	/**
	 * 根据用户Id获取personInfo信息
	 * 
	 * @param uesrId
	 * @return
	 */
	PersonInfo getPersonInfoById(int userId);
}
