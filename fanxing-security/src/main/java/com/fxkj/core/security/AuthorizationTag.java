package com.fxkj.core.security;

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
import org.springframework.util.StringUtils;
import org.springframework.web.util.ExpressionEvaluationUtils;

import com.fxkj.core.exception.ServiceException;

public class AuthorizationTag extends TagSupport{
	private static final long serialVersionUID = 2L;
	private String ifAllGranted = "";
	private String ifAnyGranted = "";
	private String ifNotGranted = "";

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public int doStartTag() throws JspException {
		if (((null == ifAllGranted) || "".equals(ifAllGranted))
				&& ((null == ifAnyGranted) || "".equals(ifAnyGranted))
				&& ((null == ifNotGranted) || "".equals(ifNotGranted))) {
			return Tag.SKIP_BODY;
		}
		final Set granted = getPrincipalAuthorities();
		final String evaledIfNotGranted = ExpressionEvaluationUtils
				.evaluateString("ifNotGranted", ifNotGranted, pageContext);
		if ((null != evaledIfNotGranted) && !"".equals(evaledIfNotGranted)) {
			Set grantedCopy = retainAll(granted,
					parseAuthoritiesString(evaledIfNotGranted));
			if (!grantedCopy.isEmpty()) {
				return Tag.SKIP_BODY;
			}
		}
		final String evaledIfAllGranted = ExpressionEvaluationUtils
				.evaluateString("ifAllGranted", ifAllGranted, pageContext);
		if ((null != evaledIfAllGranted) && !"".equals(evaledIfAllGranted)) {
			if (!granted
					.containsAll(parseAuthoritiesString(evaledIfAllGranted))) {
				return Tag.SKIP_BODY;
			}
		}
		final String evaledIfAnyGranted = ExpressionEvaluationUtils
				.evaluateString("ifAnyGranted", ifAnyGranted, pageContext);
		if ((null != evaledIfAnyGranted) && !"".equals(evaledIfAnyGranted)) {
			Set grantedCopy = retainAll(granted,
					parseAuthoritiesString(evaledIfAnyGranted));

			if (grantedCopy.isEmpty()) {
				return Tag.SKIP_BODY;
			}
		}
		return Tag.EVAL_BODY_INCLUDE;
	}

	public String getIfAllGranted() {
		return ifAllGranted;
	}

	public String getIfAnyGranted() {
		return ifAnyGranted;
	}

	public String getIfNotGranted() {
		return ifNotGranted;
	}

	/**
	 * 得到当前用户所拥有的角色
	 * 
	 * @return
	 */

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Set getPrincipalAuthorities() {
		Authentication currentUser = SecurityContextHolder.getContext()
				.getAuthentication();

		if (null == currentUser) {
			return null;
		}

		if ((null == currentUser.getAuthorities())
				|| (currentUser.getAuthorities().size() < 1)) {
			return null;
		}

		Set target = new HashSet();
		Iterator iterator = currentUser.getAuthorities().iterator();
		while (iterator.hasNext()) {
			GrantedAuthority grantedAuthority = (GrantedAuthority) iterator
					.next();
			target.add(grantedAuthority.getAuthority());
		}

		return target;
	}

	/**
	 * 根据前台标签传过来的url得到对应的角色
	 * 
	 * @param resourceUrlString
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Set parseAuthoritiesString(String resourceUrlString) {
		final Set requiredAuthorities = new HashSet();
		final String[] authorities = StringUtils
				.commaDelimitedListToStringArray(resourceUrlString);
		for (int i = 0; i < authorities.length; i++) {
			String authority = authorities[i];
			String url = authority.trim();
			url = StringUtils.replace(url, "\t", "");
			url = StringUtils.replace(url, "\r", "");
			url = StringUtils.replace(url, "\n", "");
			url = StringUtils.replace(url, "\f", "");
			Collection<ConfigAttribute> list = new MyInvocationSecurityMetadataSource()
					.getButtonPower(url);
			if (list != null && list.size() > 0) {
				for (Iterator it = list.iterator(); it.hasNext();) {
					String aString = it.next().toString();
					requiredAuthorities.add(aString);

				}
			}

		}

		return requiredAuthorities;
	}

	/**
	 * 取两个角色集合的交集
	 * 
	 * @param granted
	 * @param required
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private Set retainAll(final Set granted, final Set required) {
		if(granted==null){
			throw new ServiceException("会话超时， 请重新登录!");
		}
		//Set grantedRoles = granted;
		//Set requiredRoles = required;
		//grantedRoles.retainAll(requiredRoles);
	//	return rolesToAuthorities(required, granted);
		return rolesToAuthorities( granted,required);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Set rolesToAuthorities(Set grantedRoles, Collection granted) {
		Set target = new HashSet();
		for (Iterator iterator = grantedRoles.iterator(); iterator.hasNext();) {
			String role = (String) iterator.next();
			for (Iterator grantedIterator = granted.iterator(); grantedIterator
					.hasNext();) {
				String c = (String) grantedIterator.next();
				if (c.equals(role)) {
					target.add(c);
					break;
				}
			}
		}
		return target;
	}

	

	
	public void setIfAllGranted(String ifAllGranted) throws JspException {
		this.ifAllGranted = ifAllGranted;
	}

	public void setIfAnyGranted(String ifAnyGranted) throws JspException {
		this.ifAnyGranted = ifAnyGranted;
	}

	public void setIfNotGranted(String ifNotGranted) throws JspException {
		this.ifNotGranted = ifNotGranted;
	}
}
