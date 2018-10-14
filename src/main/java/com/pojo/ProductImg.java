package com.pojo;

import java.util.Date;

public class ProductImg {
	private Integer productImgId;

	private String imgAddress;

	private String imgDesc;

	private Integer priority;

	private Date createTime;

	private Integer productId;

	public Integer getProductImgId() {
		return productImgId;
	}

	public void setProductImgId(Integer productImgId) {
		this.productImgId = productImgId;
	}

	public String getImgAddress() {
		return imgAddress;
	}

	public void setImgAddress(String imgAddress) {
		this.imgAddress = imgAddress;
	}

	public String getImgDesc() {
		return imgDesc;
	}

	public void setImgDesc(String imgDesc) {
		this.imgDesc = imgDesc == null ? null : imgDesc.trim();
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}
}