package com.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.pojo.ProductImg;

public class ProductImgDaoTest extends BaseTest{
	@Autowired
	private ProductImgDao productImgDao;
	
	@Test
	@Ignore
	public void testBatchInsertProductImg() throws Exception {
		//productId 为1的商品里添加两个详情图片记录
		ProductImg productImg1 = new ProductImg();
		productImg1.setImgAddress("图片1");
		productImg1.setImgDesc("奶茶");
		productImg1.setPriority(1);
		productImg1.setCreateTime(new Date());
		productImg1.setProductId(1);
		ProductImg productImg2 = new ProductImg();
		productImg2.setImgAddress("图片2");
		productImg2.setImgDesc("奶茶2");
		productImg2.setPriority(1);
		productImg2.setCreateTime(new Date());
		productImg2.setProductId(1);
		List<ProductImg> productImgList = new ArrayList<ProductImg>();
		productImgList.add(productImg1);
		productImgList.add(productImg2);
		int effectedNum = productImgDao.batchInsertProductImg(productImgList);
		
		assertEquals(2, effectedNum);
	}
	
	@Test
	public void testDeleteProductImgByProductId() throws Exception{
		//删除新增的两条商品详情图片记录
		int productId = 11;
		int effectdNum = productImgDao.deleteProductImgByProductId(productId);
		assertEquals(2,effectdNum);
	}
}
