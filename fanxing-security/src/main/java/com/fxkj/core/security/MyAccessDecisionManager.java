package com.fxkj.core.security;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

/**
 * 访问决策管理器
 * 
 */

public class MyAccessDecisionManager implements AccessDecisionManager {
	/**
	 * configAttributes 请求url地址所需要的权限集合 authentication 用户权限集合
	 * 
	 * 检查 两个集合中有没有相同的值，有则放行， 没有则是未授权
	 */
	public void decide(Authentication authentication, Object object,
			Collection<ConfigAttribute> configAttributes)
			throws AccessDeniedException, InsufficientAuthenticationException {
		if (configAttributes == null) {
			return;
		}
		Iterator<ConfigAttribute> ite = configAttributes.iterator();
		while (ite.hasNext()) {
			ConfigAttribute ca = ite.next();
			String needRole = ((SecurityConfig) ca).getAttribute();
			// log.info("该请求需要的权限：" + needRole);
			for (GrantedAuthority ga : authentication.getAuthorities()) {
				// log.info("用户的权限：" + ga.getAuthority());
				if (needRole.equals(ga.getAuthority())) { // ga is user's role.
					return;
				}
			}
		}
		throw new AccessDeniedException("没有访问权限！");
	}

	public boolean supports(ConfigAttribute attribute) {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean supports(Class<?> clazz) {
		return true;
	}

}
