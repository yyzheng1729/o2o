package com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.PersonInfoDao;
import com.pojo.PersonInfo;
import com.service.PersonInfoService;

@Service
public class PersonInfoServiceImpl implements PersonInfoService{
	@Autowired
	private PersonInfoDao personInfoDao;

	@Override
	public PersonInfo getPersonInfoById(int userId) {
		return personInfoDao.queryPersonInfoById(userId);
	}
}
