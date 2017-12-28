package com.fxkj.core.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

public class CustomSimpleMappingExceptionResolver extends
		SimpleMappingExceptionResolver {

	@Override
	protected ModelAndView doResolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		String viewName = determineViewName(ex, request);
		if (viewName != null) {// JSP格式返回
			Integer statusCode = determineStatusCode(request, viewName);
			if (statusCode != null) {
				applyStatusCodeIfPossible(request, response, statusCode);
				return getModelAndView(viewName, ex, request);
			}

			/*
			 * if (!(request.getHeader("accept").indexOf("application/json") >
			 * -1 || (request .getHeader("X-Requested-With") != null && request
			 * .getHeader("X-Requested-With").indexOf("XMLHttpRequest") > -1)))
			 * {// 如果不是异步请求 Integer statusCode = determineStatusCode(request,
			 * viewName); if (statusCode != null) {
			 * applyStatusCodeIfPossible(request, response, statusCode); return
			 * getModelAndView(viewName, ex, request); } } else {// JSON格式返回 try
			 * { Map<String, Object> map = new HashMap<String, Object>();
			 * map.put("flg", false); map.put("msg", ex.getMessage());
			 * PrintWriter writer = response.getWriter();
			 * writer.print(JSONObject.fromObject(map)); writer.flush();
			 * writer.close(); } catch (IOException e) { } return new
			 * ModelAndView(); }
			 */
			return null;
		} else {
			return null;
		}
	}

}
