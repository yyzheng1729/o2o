package com.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.BaseTest;
import com.pojo.Area;

@Service
public class AreaServiceTest extends BaseTest{
	@Autowired
	private AreaService areaService;
	
	@Test
	public void testGetAreaList(){
		List<Area> areaList = areaService.getAreaList();
		assertEquals("东区",areaList.get(0).getAreaName());
	}
}
