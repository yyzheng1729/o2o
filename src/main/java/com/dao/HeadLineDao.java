package com.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pojo.HeadLine;

public interface HeadLineDao {
	/**
	 * 根据传入的条件返回指定的头条列表
	 * @param headLineCondition
	 * @return
	 */
	List<HeadLine> queryHeadLine(
			@Param("headLineCondition") HeadLine headLineCondition);
}
