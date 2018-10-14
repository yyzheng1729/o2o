package com.service;

import com.dto.UserProductMapExecution;
import com.pojo.UserProductMap;

public interface UserProductMapService {

	/**
	 * 通过传入的查询条件分页列出用户消费信息列表
	 * 
	 * @param userProductMapCondition
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	UserProductMapExecution listUserProductMap(UserProductMap userProductMapCondition, Integer pageIndex,
			Integer pageSize);

	/**
	 * 添加消费记录
	 * 
	 * @param userProductMap
	 * @return
	 * @throws RuntimeException
	 */
	UserProductMapExecution addUserProductMap(UserProductMap userProductMap) throws RuntimeException;

}
