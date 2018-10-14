package com.service;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.BaseTest;
import com.dto.ImageHolder;
import com.dto.ProductExecution;
import com.enums.ProductCategoryStateEnum;
import com.enums.ProductStateEnum;
import com.pojo.Product;
import com.pojo.ProductCategory;
import com.pojo.Shop;

@Service
public class ProductServiceTest extends BaseTest {
	@Autowired
	private ProductService productService;

	@Test
//	@Ignore
	public void testAddProduct() throws RuntimeException, FileNotFoundException {
		// 创建shopId为3且，productCategoryId为12的商品实例并给其成员变量赋值
		Product product = new Product();
		Shop shop = new Shop();
		shop.setShopId(3);
		ProductCategory pc = new ProductCategory();
		pc.setProductCategoryId(12);
		product.setShop(shop);
		product.setProductCategory(pc);
		product.setProductName("商品5");
		product.setProductDesc("商品描述5");
		product.setPriority(20);
		product.setCreateTime(new Date());
		product.setEnableStatus(ProductCategoryStateEnum.SUCCESS.getState());
		
		// 创建缩略图文件流
		File thumbnailFile = new File("C:/Users/yy/Desktop/奶茶/naicha.jpg");
		InputStream is = new FileInputStream(thumbnailFile);
		ImageHolder thumbnail = new ImageHolder(thumbnailFile.getName(), is);
		// 创建两个商品详情图文件流并将它们添加到详情图列表中
		File productImg1 = new File("C:/Users/yy/Desktop/奶茶/naicha2.jpg");
		InputStream is1 = new FileInputStream(productImg1);
		File productImg2 = new File("C:/Users/yy/Desktop/奶茶/naicha3.jpg");
		InputStream is2 = new FileInputStream(productImg2);
		List<ImageHolder> productImgList = new ArrayList<ImageHolder>();
		productImgList.add(new ImageHolder(productImg1.getName(), is1));
		productImgList.add(new ImageHolder(productImg2.getName(), is2));
		// 添加商品并验证
		ProductExecution pe = productService.addProduct(product, thumbnail, productImgList);
		assertEquals(ProductStateEnum.SUCCESS.getState(), pe.getState());
	}

	@Test
	@Ignore
	public void testModifyProduct() throws FileNotFoundException {
		// 创建shopId为3且productCategoryId为4的商品实例并给其成员变量赋值
		Product product = new Product();
		Shop shop = new Shop();
		shop.setShopId(3);
		ProductCategory pc = new ProductCategory();
		pc.setProductCategoryId(12);
		product.setProductId(13);
		product.setShop(shop);
		product.setProductCategory(pc);
		product.setProductName("正式的商品");
		product.setProductDesc("正式的商品");
		// 创建缩略图文件流
		File thumbnailFile = new File("C:/Users/yy/Desktop/奶茶/naicha.jpg");
		InputStream is = new FileInputStream(thumbnailFile);
		ImageHolder thumbnail = new ImageHolder(thumbnailFile.getName(), is);
		// 创建两个商品详情图文件流并将它们添加到详情图列表中
		File productImg1 = new File("C:/Users/yy/Desktop/奶茶/naicha2.jpg");
		InputStream is1 = new FileInputStream(productImg1);
		File productImg2 = new File("C:/Users/yy/Desktop/奶茶/naicha3.jpg");
		InputStream is2 = new FileInputStream(productImg2);
		List<ImageHolder> productImgList = new ArrayList<ImageHolder>();
		productImgList.add(new ImageHolder(productImg1.getName(), is1));
		productImgList.add(new ImageHolder(productImg2.getName(), is2));
		// 添加商品并验证
		ProductExecution pe = productService.modifyProduct(product, thumbnail, productImgList);
		assertEquals(ProductStateEnum.SUCCESS.getState(), pe.getState());
	}
}
