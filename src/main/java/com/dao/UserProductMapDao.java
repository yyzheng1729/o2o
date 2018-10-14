package com.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pojo.UserProductMap;

public interface UserProductMapDao {
	/**
	 * 
	 * @param userProductCondition
	 * @param rowIndex
	 * @param pageSize
	 * @return
	 */
	List<UserProductMap> queryUserProductMapList(@Param("userProductCondition") UserProductMap userProductCondition,
			@Param("rowIndex") int rowIndex, @Param("pageSize") int pageSize);

	/**
	 * 
	 * @param userProductCondition
	 * @return
	 */
	int queryUserProductMapCount(@Param("userProductCondition") UserProductMap userProductCondition);

	/**
	 * 
	 * @param userProductMap
	 * @return
	 */
	int insertUserProductMap(@Param("userProductMap")UserProductMap userProductMap);
}
