package com.dto;

public class WechatInfo {
	private int customerId;
	private int productId;
	private int userAwardId;
	private Long createTime;
	private int shopId;

	public int getShopId() {
		return shopId;
	}

	public void setShopId(int shopId) {
		this.shopId = shopId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getUserAwardId() {
		return userAwardId;
	}

	public void setUserAwardId(int userAwardId) {
		this.userAwardId = userAwardId;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

}
