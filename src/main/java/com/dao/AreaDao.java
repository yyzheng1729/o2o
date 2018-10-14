package com.dao;

import java.util.List;

import com.pojo.Area;

public interface AreaDao {
	/**
	 * 列出区域列表
	 * @return areaList
	 */
	List<Area> queryArea();
}
