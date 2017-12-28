package com.fxkj.security.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fxkj.core.base.BaseAction;
import com.fxkj.core.base.MethodLog;
import com.fxkj.enums.EnabledEnum;
import com.fxkj.security.bean.PermissionsBean;
import com.fxkj.security.service.PermissionsService;

@Controller
@RequestMapping("/permissionsController")
public class PermissionsController extends BaseAction {
	@Autowired
	private PermissionsService permissionsService;

	@RequestMapping(value = "getPermissionsByCurrentOperator", method = RequestMethod.POST)
	@ResponseBody
	@MethodLog(remark = "获取当前用户所有权限，不包括按钮，角色可用，权限可用 ，用于页面菜单渲染")
	public List<PermissionsBean> getPermissionsByCurrentOperator() {
		List<PermissionsBean> list = permissionsService
				.getPermissionsByCurrentOperator();
		return list;
	}
	@RequestMapping(value = "getPermissionsForAuth", method = RequestMethod.POST)
	@MethodLog(remark = "获取系统能授权的权限，并标识角色已授的权限")
	@ResponseBody
	public List<PermissionsBean> getPermissionsForAuth(Integer roleId,Boolean disabled) {
		return permissionsService.getPermissionsForAuth(roleId,EnabledEnum.ENABLED.getCode(),disabled);
	}

}
