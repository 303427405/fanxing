package com.fxkj.core.utils;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 打印访问路径的过滤器 在项目开发中用于帮助调试 在web.xml配置文件中
 * 
 * 
 */
public class PrintURLFilter implements Filter {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	public FilterConfig config;

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		String[] suffix = config.getInitParameter("suffix").split(",");
		String urlstr = getFullURL(req);
		for (int i = 0; i < suffix.length; i++) {
			if (urlstr.indexOf(suffix[i]) >= 1) {
				logger.info("[url]" + urlstr);
				break;
			}
		}
		chain.doFilter(request, response);
	}

	public String getFullURL(HttpServletRequest request) {
		StringBuffer urlstrbuf = request.getRequestURL();
		if (request.getQueryString() != null) {
			urlstrbuf.append("?");
			urlstrbuf.append(request.getQueryString());
		}
		return urlstrbuf.toString();
	}
	public void destroy() {
		this.config = null;
	}

	public void init(FilterConfig arg0) throws ServletException {
		config = arg0;
	}

}
