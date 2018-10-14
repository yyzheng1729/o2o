package com.pojo;

import java.util.Date;

//顾客消费的商品映射
public class UserProductMap {
	
	private int userProductId;
	
	private Date createTime;
	
	// 顾客信息实体类
	private PersonInfo user;
	// 商品信息实体类
	private Product product;
	// 店铺信息实体类
	private Shop shop;

	public int getUserProductId() {
		return userProductId;
	}

	public void setUserProductId(int userProductId) {
		this.userProductId = userProductId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public PersonInfo getUser() {
		return user;
	}

	public void setUser(PersonInfo user) {
		this.user = user;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

}
