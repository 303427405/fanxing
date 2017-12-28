package com.fxkj.security.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fxkj.core.base.BaseAction;
import com.fxkj.core.base.MethodLog;
import com.fxkj.core.base.MsgUtil;
import com.fxkj.enums.EnabledEnum;
import com.fxkj.security.bean.RoleSearch;
import com.fxkj.security.entity.Role;
import com.fxkj.security.service.RoleService;

@Controller
@RequestMapping("/roleController")
public class RoleController extends BaseAction {

	@Autowired
	private RoleService roleService;

	@RequestMapping(value = "findRole", method = RequestMethod.POST)
	@MethodLog(remark = "角色列表方法")
	public String findRole(RoleSearch search,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap modelMap) {
		Map<String, Object> parameterMap = getGridParam(request);
		parameterMap.put("r", search);
		modelMap.put("page", roleService.findRole(parameterMap));
		modelMap.put("r", search);
		modelMap.put("enabledEnum", EnabledEnum.values());
		return "page/role/list";
	}

	@RequestMapping(value = "add_view", method = RequestMethod.POST)
	@MethodLog(remark = "转跳添加角色页面")
	public String add_view() {
		return "page/role/add";
	}
	
	@RequestMapping(value = "edit_view", method = RequestMethod.POST)
	@MethodLog(remark = "转跳修改角色页面")
	public String edit_view(Integer id,ModelMap modelMap) {
		modelMap.put("r",roleService.edit_view(id));
		return "page/role/edit";
	}
	
	@RequestMapping(value = "view", method = RequestMethod.POST)
	public String view(Integer id,ModelMap modelMap) {
		modelMap.put("r",roleService.edit_view(id));
		return "page/role/view";
	}
	
	@RequestMapping(value = "addRole", method = RequestMethod.POST)
	@ResponseBody
	@MethodLog(remark = "添加角色")
	public MsgUtil addRole(Role r) {
		return roleService.addRole(r);
	}
	@RequestMapping(value = "updateRoleById", method = RequestMethod.POST)
	@ResponseBody
	@MethodLog(remark = "修改角色")
	public MsgUtil updateRoleById(Role r) {
		return roleService.updateRoleById(r);
	}


	@RequestMapping(value = "changeEnabled", method = RequestMethod.POST)
	@ResponseBody
	@MethodLog(remark = "改变角色状态")
	public MsgUtil changeEnabled(Integer id, Boolean flg) {
		return roleService.changeEnabled(id, flg);
	}


}
