package com.fxkj.security.entity;

import com.fxkj.core.base.BaseEntity;

@SuppressWarnings("serial")
public class Operator extends BaseEntity {
	private Integer id;
	private String loginName;
	private String realName;
	private String password;
	private String email;
	private String phone;// 电话
	private String mobile;
	private Integer enabled; // 是否启用 1：启用 0：禁用
	private Integer accountNonExpired;// 锁定帐号 1:正常 0：锁定

	private Integer isLockScreen;// 锁屏状态 1： 锁定 0：未锁定

	private String imgPath;
	private String orgCode;
	private String orgName;
	private String areaCodes;

	private String validDate;

	public String getValidDate() {
		return validDate;
	}

	public void setValidDate(String validDate) {
		this.validDate = validDate;
	}

	public String getAreaCodes() {
		return areaCodes;
	}

	public void setAreaCodes(String areaCodes) {
		this.areaCodes = areaCodes;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public Integer getIsLockScreen() {
		return isLockScreen;
	}

	public void setIsLockScreen(Integer isLockScreen) {
		this.isLockScreen = isLockScreen;
	}

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	public Integer getAccountNonExpired() {
		return accountNonExpired;
	}

	public void setAccountNonExpired(Integer accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public String getLoginName() {
		return loginName;
	}

	public String getPassword() {
		return password;
	}

	public String getRealName() {
		return realName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}
}
