package com.fxkj.core.security.tag;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import com.fxkj.core.exception.ServiceException;
import com.fxkj.core.security.MyInvocationSecurityMetadataSource;
import com.fxkj.core.utils.StringUtil;

public class AuthorizationTag extends TagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public int doStartTag() throws JspException {

		final Set<String> operatorAuth = getOperatorAuth();

		if (StringUtil.isNotEmpty(ifNotAuth)) {
			Set<String> grantedCopy = retainAll(operatorAuth,
					parseBtnUrlForAuth(ifNotAuth));
			if (!grantedCopy.isEmpty()) {
				return Tag.SKIP_BODY;
			}
		}
		if (StringUtil.isNotEmpty(ifAllAuth)) {
			if (!operatorAuth.containsAll(parseBtnUrlForAuth(ifAllAuth))) {
				return Tag.SKIP_BODY;
			}
		}
		if (StringUtil.isNotEmpty(ifAnyAuth)) {
			Set<String> grantedCopy = retainAll(operatorAuth,
					parseBtnUrlForAuth(ifAnyAuth));
			if (grantedCopy.isEmpty()) {
				return Tag.SKIP_BODY;
			}
		}

		return Tag.EVAL_BODY_INCLUDE;
	}

	/**
	 * 取两个角色集合的交集
	 * 
	 * @param
	 * @param
	 * @return
	 */
	private Set<String> retainAll(final Set<String> operatorAuth,
			final Set<ConfigAttribute> systemAuth) {
		Set<String> target = new HashSet<String>();
		for (Iterator<String> operator = operatorAuth.iterator(); operator
				.hasNext();) {
			String roleId = operator.next();
			for (Iterator<ConfigAttribute> system = systemAuth.iterator(); system
					.hasNext();) {
				String roleIdSys = system.next().getAttribute();
				if (roleIdSys.equals(roleId)) {
					target.add(roleIdSys);
					break;
				}
			}
		}
		return target;
	}

	/**
	 * 得到当前用户所拥有的角色
	 * 
	 * @return
	 */

	private Set<String> getOperatorAuth() {
		Authentication currentUser = SecurityContextHolder.getContext()
				.getAuthentication();
		if (null == currentUser) {
			throw new ServiceException("会话超时， 请重新登录!");
		}
		Set<String> roleIdSet = new HashSet<String>();
		if ((null == currentUser.getAuthorities())
				|| (currentUser.getAuthorities().size() == 0)) {
			return roleIdSet;
		}
		@SuppressWarnings("rawtypes")
		Iterator iterator = currentUser.getAuthorities().iterator();
		while (iterator.hasNext()) {
			GrantedAuthority grantedAuthority = (GrantedAuthority) iterator
					.next();
			roleIdSet.add(grantedAuthority.getAuthority());
		}
		return roleIdSet;
	}

	/**
	 * 根据前台标签传过来的url得到系统对应的角色
	 * 
	 * @param
	 * @return
	 */
	private Set<ConfigAttribute> parseBtnUrlForAuth(String btnUrl) {
		final Set<ConfigAttribute> requiredAuthorities = new HashSet<ConfigAttribute>();
		Collection<ConfigAttribute> list = new MyInvocationSecurityMetadataSource()
				.getButtonPower(btnUrl);
		
		requiredAuthorities.addAll(list);
		
//		if (list != null && list.size() > 0) {
//			for (Iterator<ConfigAttribute> it = list.iterator(); it.hasNext();) {
//				String roleId = it.next().toString();
//				requiredAuthorities.add(roleId);
//			}
//		}

		return requiredAuthorities;
	}

	private String ifAllAuth;
	private String ifAnyAuth;
	private String ifNotAuth;

	public String getIfAllAuth() {
		return ifAllAuth;
	}

	public void setIfAllAuth(String ifAllAuth) {
		this.ifAllAuth = ifAllAuth;
	}

	public String getIfAnyAuth() {
		return ifAnyAuth;
	}

	public void setIfAnyAuth(String ifAnyAuth) {
		this.ifAnyAuth = ifAnyAuth;
	}

	public String getIfNotAuth() {
		return ifNotAuth;
	}

	public void setIfNotAuth(String ifNotAuth) {
		this.ifNotAuth = ifNotAuth;
	}

}
