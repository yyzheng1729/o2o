package com.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.pojo.Product;
import com.pojo.ProductCategory;
import com.pojo.ProductImg;
import com.pojo.Shop;

public class ProductDaoTest extends BaseTest{
	@Autowired
	private ProductDao productDao;
	@Autowired
	private ProductImgDao productImgDao;
	
	@Test
	@Ignore
	public void testInsertProduct() throws Exception{
		Shop shop1 = new Shop();
		shop1.setShopId(3);
		ProductCategory pc1 = new ProductCategory();
		pc1.setProductCategoryId(12);
		//初始化三个商品实例并添加进shopId为3的店铺里
		//同时类别Id为12
		Product product1 = new Product();
		product1.setProductName("商品1");
		product1.setProductDesc("商品Desc1");
		product1.setImgAddr("test1");
		product1.setPriority(0);
		product1.setEnableStatus(1);
		product1.setCreateTime(new Date());
		product1.setLastEditTime(new Date());
		product1.setShop(shop1);
		product1.setProductCategory(pc1);
		Product product2 = new Product();
		product2.setProductName("商品2");
		product2.setProductDesc("商品Desc2");
		product2.setImgAddr("test2");
		product2.setPriority(0);
		product2.setEnableStatus(0);
		product2.setCreateTime(new Date());
		product2.setLastEditTime(new Date());
		product2.setShop(shop1);
		product2.setProductCategory(pc1);
		Product product3 = new Product();
		product3.setProductName("商品3");
		product3.setProductDesc("商品Desc3");
		product3.setImgAddr("test3");
		product3.setPriority(0);
		product3.setEnableStatus(1);
		product3.setCreateTime(new Date());
		product3.setLastEditTime(new Date());
		product3.setShop(shop1);
		product3.setProductCategory(pc1);
		//判断添加是否成功
		int effectedNum = productDao.insertProduct(product1);
		assertEquals(1, effectedNum);
		effectedNum = productDao.insertProduct(product2);
		assertEquals(1, effectedNum);
		effectedNum = productDao.insertProduct(product3);
		assertEquals(1, effectedNum);
	}
	
	@Test
//	@Ignore
	public void testQueryProductByproductId(){
		int productId = 1;
		//初始化两个商品详情图实例作为productId为12的商品下的详情图片
		//批量插入到商品详情图列表中
//		ProductImg productImg1 = new ProductImg();
//		productImg1.setImgAddr("tupianOne");
//		productImg1.setImgDesc("ceshi1111");
//		productImg1.setPriority(1);
//		productImg1.setCreateTime(new Date());
//		productImg1.setProductId(productId);
//		ProductImg productImg2 = new ProductImg();
//		productImg2.setImgAddr("tupianTeo");
//		productImg2.setImgDesc("ceshi2222");
//		productImg2.setPriority(1);
//		productImg2.setCreateTime(new Date());
//		productImg2.setProductId(productId);
//		List<ProductImg> productImgList = new ArrayList<ProductImg>();
//		productImgList.add(productImg1);
//		productImgList.add(productImg2);
//		int effectedNum = productImgDao.batchInsertProductImg(productImgList);
//		assertEquals(2,effectedNum);
		//查询productId为11的商品信息并校验返回的详情图实例列表size是否为2
		Product product = productDao.queryProductByProductId(productId);
		List<ProductImg> list = product.getProductImgList();
		for(int i=0; i<list.size(); i++){
			System.out.println(list.get(i).getImgAddress()+"=========="+i);
		}
		System.out.println(product.getImgAddr()+"==================555555555555555");
		assertEquals(2,product.getProductImgList().size());
		//删除新增的这两个商品详情图实例
//		effectedNum = productImgDao.deleteProductImgByProductId(productId);
//		assertEquals(2,effectedNum);
	}
	
	@Test
	@Ignore
	public void testUpdateProduct(){
		Product product = new Product();
		ProductCategory pc = new ProductCategory();
		Shop shop = new Shop();
		shop.setShopId(3);
		pc.setProductCategoryId(5);
		product.setProductId(12);
		product.setShop(shop);
		product.setProductName("第一个产品");
		product.setProductCategory(pc);
		//修改productId为12的商品的名称
		//以及商品类别并校验影响的行数是否为1
		int effectedNum = productDao.updateProduct(product);
		assertEquals(1,effectedNum);
	}
	
	@Test
	@Ignore
	public void testQueryProductList(){
		Product productCondition = new Product();
		//分页查询，预期返回三条结果
		List<Product> productList = productDao.queryProductList(productCondition, 0, 3);
		assertEquals(3, productList.size());
		//查询名称为测试的商品总数
		int count = productDao.queryProductCount(productCondition);
		assertEquals(10, count);
		//使用商品名称模糊查询，预期返回两条结果
		productCondition.setProductName("商品");
		productList = productDao.queryProductList(productCondition, 0, 6);
		assertEquals(5, productList.size());
		count = productDao.queryProductCount(productCondition);
		assertEquals(5, count);
	}
	
	@Test
	@Ignore
	public void testUpdateProductCategoryToNull(){
		//将productCategoryId为2的商品类别下面的商品类别置为空
		int effectedNum = productDao.updateProductCategoryToNull(14);
		assertEquals(3, effectedNum);
	}
	
	@Test
	@Ignore
	public void test(){
		//清除掉insert方法添加的商品
		Product productCondition = new Product();
		ProductCategory pc = new ProductCategory();
		pc.setProductCategoryId(14);
		productCondition.setProductCategory(pc);
		//通过输入productCategoryId为1去商品表中查出相关的三条记录
		List<Product> productList = productDao.queryProductList(productCondition, 0, 3);
		assertEquals(3, productList.size());
		//循环删除这三条数据
	}
}
