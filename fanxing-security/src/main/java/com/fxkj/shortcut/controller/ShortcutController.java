package com.fxkj.shortcut.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fxkj.core.base.BaseAction;
import com.fxkj.core.base.MethodLog;
import com.fxkj.core.base.MsgUtil;
import com.fxkj.core.base.PageInfo;
import com.fxkj.core.common.SecurityUtils;
import com.fxkj.security.bean.PermissionsBean;
import com.fxkj.shortcut.bean.ShortcutSearch;
import com.fxkj.shortcut.entity.Shortcut;
import com.fxkj.shortcut.service.ShortcutService;

@Controller
@RequestMapping("/shortcutController")
public class ShortcutController extends BaseAction {

	@Autowired
	private ShortcutService shortcutService;

	@RequestMapping(value = "findShortcut")
	public String findShortcut(ShortcutSearch search,
			HttpServletRequest request, ModelMap modelMap) {
		Map<String, Object> parameterMap = getGridParam(request);
		Integer operatorId = SecurityUtils.getCurrentOperator().getId();
		if (operatorId == null) {
			modelMap.put("page", new PageInfo<Shortcut>());
			modelMap.put("s", search);
			return "page/shortcut/list";
		}
		search.setOperatorId(operatorId);
		parameterMap.put("s", search);
		modelMap.put("page", shortcutService.findShortcut(parameterMap));
		modelMap.put("s", search);
		return "page/shortcut/list";
	}

	@RequestMapping(value = "add_view", method = RequestMethod.POST)
	@MethodLog(remark = "转跳添加快捷方式页面")
	public String add_view(ModelMap modelMap) {
		return "page/shortcut/add";
	}

	@RequestMapping(value = "getOperatorPermissionsForShortcut", method = RequestMethod.POST)
	@ResponseBody
	public List<PermissionsBean> getOperatorPermissionsForShortcut(
			ModelMap modelMap) {
		Integer operatorId = SecurityUtils.getCurrentOperator().getId();
		List<PermissionsBean> list = new ArrayList<PermissionsBean>();
		if (operatorId != null) {
			list = shortcutService
					.getOperatorPermissionsForShortcut(operatorId);
		}
		return list;
	}

	@RequestMapping(value = "addShortcut", method = RequestMethod.POST)
	@ResponseBody
	public MsgUtil addShortcut(Shortcut s) {
		return shortcutService.addShortcut(s);
	}

	@RequestMapping(value = "delShortcut", method = RequestMethod.POST)
	@ResponseBody
	public MsgUtil delShortcut(String ids) {
		return shortcutService.delShortcut(ids);
	}
}
