package com.fxkj.core.filter;



public class LockScreenUserBean  {
	private Integer id;
	private Integer isLockScreen;// 锁屏状态 1： 锁定 0：未锁定

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIsLockScreen() {
		return isLockScreen;
	}

	public void setIsLockScreen(Integer isLockScreen) {
		this.isLockScreen = isLockScreen;
	}

}
