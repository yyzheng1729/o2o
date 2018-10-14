package com.dao;



import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.pojo.HeadLine;

public class HeadLineDaoTest extends BaseTest{
	@Autowired
	private HeadLineDao headLineDao;
	
	@Test
	public void testQueryHeadLine(){
		List<HeadLine> headLineList = headLineDao.queryHeadLine(new HeadLine());
		assertEquals(3,headLineList.size());
	}
}
