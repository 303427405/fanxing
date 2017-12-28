package com.fxkj.core.common;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;

import com.fxkj.core.security.MyUserDetailInfo;
import com.fxkj.core.utils.StringUtil;

public final class SecurityUtils {
	public static String getCurrentOperatorName() {
		String loginName;
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			UserDetails userDetails = (UserDetails) principal;
			loginName = userDetails.getUsername();
		} else {
			loginName = principal.toString();
		}
		return loginName;
	}

	public static MyUserDetailInfo getCurrentOperator() {
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			MyUserDetailInfo userDetails = (MyUserDetailInfo) principal;
			return userDetails;
		} else {
			return null;
		}

	}

	/**
	 * 获取在线用户信息
	 * 
	 * @param sessionRegistry
	 * @return
	 */
	public static List<MyUserDetailInfo> getOnLineOperator(
			SessionRegistry sessionRegistry, String name, String orgCode) {
		List<MyUserDetailInfo> userList = new ArrayList<MyUserDetailInfo>();
		List<Object> list = sessionRegistry.getAllPrincipals();
		for (Object o : list) {
			MyUserDetailInfo user = (MyUserDetailInfo) o;
			List<SessionInformation> infoList = sessionRegistry.getAllSessions(
					user, false);
			for (SessionInformation s : infoList) {
				MyUserDetailInfo my = (MyUserDetailInfo) s.getPrincipal();
				my.setLastLoginDate(s.getLastRequest());
				if (StringUtil.isNotEmpty(orgCode)) {
					if (my.getOrgCode().startsWith(orgCode)) {
						boolean flg = true;
						if (StringUtil.isNotEmpty(name)) {
							flg = false;
							if (my.getUsername().contains(name)) {
								userList.add(my);
							}
						}
						if (flg)
							userList.add(my);
					}
				} else {
					userList.add(my);
				}
			}
		}
		return userList;
	}

	public static boolean hasLogin() {
		return SecurityContextHolder.getContext().getAuthentication() != null;
	}

}
