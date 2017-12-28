package com.fxkj.login.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fxkj.core.base.BaseAction;
import com.fxkj.core.base.MethodLog;
import com.fxkj.core.common.SecurityUtils;
import com.fxkj.login.service.LoginService;
import com.fxkj.security.bean.OperatorSearch;
import com.fxkj.security.service.OperatorService;
import com.fxkj.security.service.PermissionsService;
import com.fxkj.security.service.RoleService;
import com.fxkj.shortcut.bean.ShortcutBean;
import com.fxkj.shortcut.service.ShortcutService;

@Controller
@RequestMapping("/loginController")
public class LoginController extends BaseAction {
	@Autowired
	private PermissionsService permissionsService;
	@Autowired
	private ShortcutService shortcutService;

	@Autowired
	private LoginService loginService;
	@Autowired
	private RoleService roleService;

	@Autowired
	private OperatorService operatorService;

	@RequestMapping(value = "findOnLineUser")
	@MethodLog(remark = "获取在线用户列表")
	public String findOnLineUser(OperatorSearch search, ModelMap modelMap) {
		modelMap.put("page", loginService.findOnLineUser(search));
		modelMap.put("o", search);
		return "page/operator/onLinelist";
	}

	@RequestMapping(value = "/system_main", method = RequestMethod.GET)
	@MethodLog(remark = "系统主页")
	public String main(ModelMap modelMap) {
		modelMap.put("permissionsList",
				permissionsService.getPermissionsByCurrentOperator());
		modelMap.put("shortcutList", (List<ShortcutBean>) shortcutService
				.getShortcutByCurrentOperator());
		return "page/main/main";
	}

	@RequestMapping(value = "/loginNoAuth")
	@MethodLog(remark = "登录")
	public String loginNoAuth() {
		return "page/login/login";
	}

	@RequestMapping(value = "/expiredDateNoAuth")
	@MethodLog(remark = "用户过期")
	public String expiredDateNoAuth() {
		return "page/login/expiredDate";
	}

	@RequestMapping(value = "/system_right", method = RequestMethod.POST)
	@MethodLog(remark = "系统右边页")
	public String system_right() {
		return "page/main/right";
	}

	@RequestMapping(value = "/profile", method = RequestMethod.POST)
	@MethodLog(remark = "个人资料")
	public String profile(ModelMap modelMap) {
		if (SecurityUtils.getCurrentOperator() != null) {
			Integer id = SecurityUtils.getCurrentOperator().getId();
			modelMap.put("o", operatorService.getOperatorById(id));
			modelMap.put("list", roleService.getEnabledRoleListByOperatorId(id));
		}

		return "page/main/profile";
	}

	@RequestMapping(value = "/lockScreen", method = RequestMethod.GET)
	@MethodLog(remark = "锁屏")
	public String lockScreen(HttpServletRequest request, ModelMap modelMap) {
		String msg = loginService.lockScreen(request);
		if (msg != null) {
			modelMap.put("msg", msg);
		}
		return "page/main/lockScreen";
	}

	@RequestMapping(value = "/unLockScreen", method = RequestMethod.POST)
	@MethodLog(remark = "解锁屏幕")
	public String unLockScreen(String password, HttpServletRequest request,
			ModelMap modelMap) {
		String msg = loginService.unLockScreen(request, password);
		if (msg == null) {
			return "redirect:system_main.do";
		} else {
            modelMap.put("msg", msg);
            return "page/main/lockScreen";
        }

	}

}
