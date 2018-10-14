package com.service;

import com.dto.ImageHolder;
import com.dto.ShopExecution;
import com.pojo.Shop;

public interface ShopService {
	/**
	 * 创建商铺
	 * 
	 * @param Shop shop
	 * @return ShopExecution shopExecution
	 * @throws ShopOperationException
	 */
	ShopExecution addShop(Shop shop, ImageHolder thumbnail) throws RuntimeException;

	/**
	 * 查询指定店铺信息
	 * 
	 * @param long
	 *            shopId
	 * @return Shop shop
	 */
	Shop getByShopId(int shopId);

	/**
	 * 更新店铺信息（从店家角度）
	 * 
	 * @param areaId
	 * @param shopAddr
	 * @param phone
	 * @param shopImg
	 * @param shopDesc
	 * @return ShopExecution shopExecution
	 * @throws ShopOperationException
	 */
	ShopExecution modifyShop(Shop shop, ImageHolder thumbnail) throws RuntimeException;
	
	/**
	 * 根据shopCondition分页返回相应列表数据
	 * @param shopCondition
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public ShopExecution getShopList(Shop shopCondition,int pageIndex,int pageSize);
}
