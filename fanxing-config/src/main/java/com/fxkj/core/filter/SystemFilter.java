package com.fxkj.core.filter;

import java.io.IOException;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.context.SecurityContextImpl;

import com.fxkj.core.utils.ProperitiesUtil;
import com.fxkj.core.utils.StringUtil;

/**
 * 系统过渡器
 * 
 * 
 */
public class SystemFilter implements Filter {

	private static final String TIMEOUT_PATH = "/commonController/timeout.do";

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		if (!checkLockScreen(req, res)) {
			return;
		}

		Map<String, String[]> parameterMap = req.getParameterMap();
		// 校验参数值有无特殊标签
		for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
			String[] value = entry.getValue();
			if (entry.getKey().equals("ajaxType")) {// 标记发的ajax请求
				SecurityContextImpl s = (SecurityContextImpl) req.getSession()
						.getAttribute("SPRING_SECURITY_CONTEXT");
				if (s == null) {
					boolean flg = checkSessionForAjax(req, res);
					if (!flg) {
						return;
					}
				}
			}
			if (value != null) {
				int strLength = value.length;
				for (int i = 0; i < strLength; i++) {
					boolean result = stripXSS(value[i]);
					if (result) {
						res.sendRedirect(req.getContextPath() + TIMEOUT_PATH);
						return;
					}
				}
			}
		}
		chain.doFilter(request, response);
	}

	/**
	 * 校验屏幕是否锁定
	 * 
	 * @param req
	 * @param res
	 */
	public boolean checkLockScreen(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String url = req.getRequestURL().toString();
		if (url.endsWith(".do")) {
			HttpSession session = req.getSession(false);
			if (session != null) {
				LockScreenUserBean user = (LockScreenUserBean) session
						.getAttribute("lockScreenUser");
				if (user != null) {
					if (user.getIsLockScreen() != null
							&& 1 == user.getIsLockScreen()
							&& !url.endsWith("/lockScreen.do")
							&& !url.endsWith("/unLockScreen.do")) {
						res.sendRedirect(req.getContextPath()
								+ "/loginController/lockScreen.do");
						return false;
					}
				}
			}
		}
		return true;

	}

	/**
	 * ajax提交时校验session是否过期
	 * 
	 * @param flg
	 * @param req
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public boolean checkSessionForAjax(HttpServletRequest req,
			HttpServletResponse response) throws IOException {
		String url = req.getRequestURL().toString();
		if (url.endsWith(".do")) {
			String excludeUrl = ProperitiesUtil.getValue("excludeAjaxUrl");
			boolean flgUrl = true;
			if (StringUtil.isNotEmpty(excludeUrl)) {
				String[] array = excludeUrl.split(";");
				for (int i = 0; i < array.length; i++) {
					if (url.endsWith(array[i])) {
						flgUrl = false;
						break;
					}
				}
			}
			if (flgUrl == true) {
				HttpSession session = req.getSession();
				SecurityContextImpl s = (SecurityContextImpl) session
						.getAttribute("SPRING_SECURITY_CONTEXT");
				if (s == null) {
					response.sendRedirect(req.getContextPath() + TIMEOUT_PATH);
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * 参数判断
	 * 
	 * @param value
	 * @return
	 */
	public static boolean stripXSS(String value) {
		boolean result = false;
		if (value != null) {
			value = value.replaceAll("", "");

			// Avoid anything between script tags
			Pattern scriptPattern = Pattern.compile("<script>(.*?)</script>",
					Pattern.CASE_INSENSITIVE);
			result = scriptPattern.matcher(value).find();// .replaceAll("");
			// //如果找到则为true
			if (result)
				return result;

			// Avoid anything in a src='...' type of expression
			scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'",
					Pattern.CASE_INSENSITIVE | Pattern.MULTILINE
							| Pattern.DOTALL);
			result = scriptPattern.matcher(value).find();// .replaceAll("");
			if (result)
				return result;

			scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"",
					Pattern.CASE_INSENSITIVE | Pattern.MULTILINE
							| Pattern.DOTALL);
			result = scriptPattern.matcher(value).find();// .replaceAll("");
			if (result)
				return result;

			// Remove any lonesome </script> tag
			scriptPattern = Pattern.compile("</script>",
					Pattern.CASE_INSENSITIVE);
			result = scriptPattern.matcher(value).find();// .replaceAll("");
			if (result)
				return result;

			// Remove any lonesome <script ...> tag
			scriptPattern = Pattern.compile("<script(.*?)>",
					Pattern.CASE_INSENSITIVE | Pattern.MULTILINE
							| Pattern.DOTALL);
			result = scriptPattern.matcher(value).find();// .replaceAll("");
			if (result)
				return result;

			// Avoid eval(...) expressions
			scriptPattern = Pattern.compile("eval\\((.*?)\\)",
					Pattern.CASE_INSENSITIVE | Pattern.MULTILINE
							| Pattern.DOTALL);
			result = scriptPattern.matcher(value).find();// .replaceAll("");
			if (result)
				return result;

			// Avoid expression(...) expressions
			scriptPattern = Pattern.compile("expression\\((.*?)\\)",
					Pattern.CASE_INSENSITIVE | Pattern.MULTILINE
							| Pattern.DOTALL);
			result = scriptPattern.matcher(value).find();// .replaceAll("");
			if (result)
				return result;

			scriptPattern = Pattern.compile("vbscript:",
					Pattern.CASE_INSENSITIVE);
			result = scriptPattern.matcher(value).find();// .replaceAll("");
			if (result)
				return result;

			// Avoid onload= expressions
			scriptPattern = Pattern.compile("onload(.*?)=",
					Pattern.CASE_INSENSITIVE | Pattern.MULTILINE
							| Pattern.DOTALL);
			result = scriptPattern.matcher(value).find();// .replaceAll("");
			if (result)
				return result;

			// Avoid alert:... expressions
			scriptPattern = Pattern.compile("alert", Pattern.CASE_INSENSITIVE);
			result = scriptPattern.matcher(value).find();// .replaceAll("");
			if (result)
				return result;
		}
		return result;
	}

	public void destroy() {

	}

	public void init(FilterConfig arg0) throws ServletException {
	}

}
