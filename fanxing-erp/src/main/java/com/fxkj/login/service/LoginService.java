package com.fxkj.login.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.fxkj.core.security.MyUserDetailInfo;
import com.fxkj.security.bean.OperatorSearch;

public interface LoginService {

	/**
	 * 锁屏
	 * 
	 * @param request
	 * @return
	 */
	public String lockScreen(HttpServletRequest request);

	/**
	 * 解锁屏幕
	 * 
	 * @param request
	 * @param password
	 * @return
	 */
	public String unLockScreen(HttpServletRequest request, String password);

	public List<MyUserDetailInfo> findOnLineUser(OperatorSearch search);

}
