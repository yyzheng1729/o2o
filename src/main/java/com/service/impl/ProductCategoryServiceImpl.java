package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.ProductCategoryDao;
import com.dao.ProductDao;
import com.dto.ProductCategoryExecution;
import com.enums.ProductCategoryStateEnum;
import com.pojo.ProductCategory;
import com.service.ProductCategoryService;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
	@Autowired
	ProductCategoryDao productCategoryDao;
	@Autowired
	ProductDao productDao;

	@Override
	public List<ProductCategory> getProductCategoryList(int shopId) {
		return productCategoryDao.queryProductCategoryList(shopId);
	}

	@Override
	@Transactional
	public ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategoryList)
			throws RuntimeException {
		if (productCategoryList != null && productCategoryList.size() > 0) {
			try {
				int effectedNum = productCategoryDao.batchInsertProductCategory(productCategoryList);
				if (effectedNum <= 0) {
					throw new RuntimeException("店铺类别失败");
				} else {
					return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
				}
			} catch (Exception e) {
				throw new RuntimeException("batchAddProductCategory error: " + e.getMessage());
			}
		} else {
			return new ProductCategoryExecution(ProductCategoryStateEnum.INNER_ERROR);
		}
	}

	@Override
	@Transactional
	public ProductCategoryExecution deleteProductCategory(int productCategoryId, int shopId) throws RuntimeException {
		// 解除tb_product里的商品与该productcategoryId的关联
		try {
			int effectedNum = productDao.updateProductCategoryToNull(productCategoryId);
			if (effectedNum < 0) {
				throw new RuntimeException("商品类别更新失败");
			}
		} catch (Exception e) {
			throw new RuntimeException("deleProductCategory error:" + e.getMessage());
		}
		// 删除该productCategory
		try {
			int effectedNum = productCategoryDao.deleteProductCategory(productCategoryId, shopId);
			if (effectedNum <= 0) {
				throw new RuntimeException("类别删除失败");
			} else {
				return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
			}
		} catch (Exception e) {
			throw new RuntimeException("deleteProductCategory error:" + e.getMessage());
		}
	}
}
