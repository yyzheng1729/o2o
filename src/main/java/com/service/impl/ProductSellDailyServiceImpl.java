package com.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.ProductSellDailyDao;
import com.pojo.ProductSellDaily;
import com.service.ProductSellDailyService;
@Service
public class ProductSellDailyServiceImpl implements ProductSellDailyService{
	@Autowired
	private ProductSellDailyDao productSellDailyDao;
	
	@Override
	public void dailyCalculate() {
		//统计在tb_user_product_map里面产生销量的每个店铺的隔间商品的日销量
		productSellDailyDao.insertProductSellDaily();
		//统计余下的商品的日销量，全部置为0（为了迎合echarts的数据请求）
		productSellDailyDao.insertDefaultProductSellDaily();
	}

	@Override
	public List<ProductSellDaily> listProductSellDaily(ProductSellDaily productSellDailyCondition, Date beginTime,
			Date endTime) {
		return productSellDailyDao.queryProductSellDailyList(productSellDailyCondition, beginTime, endTime);
	}
	
}
