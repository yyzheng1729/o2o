package com.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.UserProductMapDao;
import com.dto.UserProductMapExecution;
import com.enums.UserProductMapStateEnum;
import com.pojo.UserProductMap;
import com.service.UserProductMapService;
import com.util.PageCalculator;

@Service
public class UserProductMapServiceImpl implements UserProductMapService {
	@Autowired
	private UserProductMapDao userProductMapDao;

	@Override
	public UserProductMapExecution listUserProductMap(UserProductMap userProductMapCondition, Integer pageIndex,
			Integer pageSize) {
		// 空值判断
		if (userProductMapCondition != null && pageIndex != null && pageSize != null) {
			// 页转行
			int beginIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
			// 依据查询条件分页取出列表
			List<UserProductMap> userProductMapList = userProductMapDao.queryUserProductMapList(userProductMapCondition,
					beginIndex, pageSize);
			int count = userProductMapDao.queryUserProductMapCount(userProductMapCondition);
			UserProductMapExecution se = new UserProductMapExecution();
			se.setUserProductMapList(userProductMapList);
			se.setCount(count);
			return se;
		} else {
			return null;
		}
	}

	@Override
	public UserProductMapExecution addUserProductMap(UserProductMap userProductMap) throws RuntimeException {
		// 空值判断，主要确保顾客ID，店铺ID以及操作员ID非空
		if (userProductMap != null && userProductMap.getUser().getUserId() != null
				&& userProductMap.getShop().getShopId() != null) {
			// 设定默认值
			userProductMap.setCreateTime(new Date());
			// 添加消费记录
			int effectedNum = userProductMapDao.insertUserProductMap(userProductMap);
			if (effectedNum <= 0) {
				throw new RuntimeException("添加消费记录失败");
			}
			return new UserProductMapExecution(UserProductMapStateEnum.SUCCESS, userProductMap);
		} else {
			return new UserProductMapExecution(UserProductMapStateEnum.NULL_USERPRODUCT_INFO);
		}
	}

}
