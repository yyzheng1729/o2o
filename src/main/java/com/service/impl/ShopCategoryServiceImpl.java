package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.ShopCategoryDao;
import com.pojo.ShopCategory;
import com.service.ShopCategoryService;
@Service
public class ShopCategoryServiceImpl implements ShopCategoryService {
	@Autowired
	private ShopCategoryDao shopCategoryDao;
	
	@Override
	public List<ShopCategory> getShopCategory(ShopCategory shopCategoryCondition) {
		return shopCategoryDao.queryShopCategory(shopCategoryCondition);
	}
	
}
