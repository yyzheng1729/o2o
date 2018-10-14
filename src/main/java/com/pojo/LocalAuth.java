package com.pojo;

import java.util.Date;

public class LocalAuth {
	private Integer localAuthId;

	private Integer userId;

	private String username;

	private String password;

	private Date createTime;

	private Date lastEditTime;

	// 个人信息，关系为一一对应
	private PersonInfo personInfo;

	public PersonInfo getPersonInfo() {
		return personInfo;
	}

	public void setPersonInfo(PersonInfo personInfo) {
		this.personInfo = personInfo;
	}

	public Integer getLocalAuthId() {
		return localAuthId;
	}

	public void setLocalAuthId(Integer localAuthId) {
		this.localAuthId = localAuthId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username == null ? null : username.trim();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastEditTime() {
		return lastEditTime;
	}

	public void setLastEditTime(Date lastEditTime) {
		this.lastEditTime = lastEditTime;
	}
}