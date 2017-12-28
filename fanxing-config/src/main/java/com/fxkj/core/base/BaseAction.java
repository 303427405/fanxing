package com.fxkj.core.base;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fxkj.core.common.Commons;
import com.fxkj.core.utils.StringUtil;

public class BaseAction {
	
	public final Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 分页参数 分页时必须调用这方法
	 * @param request
	 * @return
	 */
	protected Map<String, Object> getGridParam(HttpServletRequest request) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		if (StringUtil.isNotEmpty(request.getParameter("pageNum"))) {
			Integer pageNum = Integer.valueOf(request.getParameter("pageNum"));
			paramMap.put("pageNum", pageNum-1);
		}else{
			paramMap.put("pageNum", 0);
		}
		if (StringUtil.isNotEmpty(request.getParameter("pageSize"))) {
			Integer pageSize = Integer.valueOf(request.getParameter("pageSize"));
			paramMap.put("pageSize", pageSize);
		}else{
			paramMap.put("pageSize", Commons.PAGE_SIZE);
		}
		if (StringUtil.isNotEmpty(request.getParameter("sort"))) {
			String sort = request.getParameter("sort");
			paramMap.put("sort", sort);
		}
		return paramMap;
	}
}
