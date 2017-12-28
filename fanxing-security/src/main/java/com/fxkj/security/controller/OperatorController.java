package com.fxkj.security.controller;

import java.io.IOException;
import java.util.List;
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
import com.fxkj.core.common.Commons;
import com.fxkj.core.common.SecurityUtils;
import com.fxkj.core.utils.StringUtil;
import com.fxkj.security.bean.OperatorSearch;
import com.fxkj.security.bean.RoleBean;
import com.fxkj.security.entity.Operator;
import com.fxkj.security.service.OperatorService;
import com.fxkj.security.service.RoleService;

@Controller
@RequestMapping("/operatorController")
public class OperatorController extends BaseAction {

	@Autowired
	private OperatorService operatorService;
	@Autowired
	private RoleService roleService;

	@RequestMapping(value = "findOperator")
	@MethodLog(remark = "用户列表方法")
	public String findOperator(OperatorSearch search,
			HttpServletRequest request, ModelMap modelMap) {
		Map<String, Object> parameterMap = getGridParam(request);
		if(StringUtil.isEmpty(search.getOrgCode())){
			String orgCode=SecurityUtils.getCurrentOperator().getOrgCode();
			if(StringUtil.isNotEmpty(orgCode)&&!orgCode.startsWith(Commons.COUNTRYCODE)){
				search.setAreaCode(orgCode.substring(0,orgCode.indexOf("_")));
			}
		}
		parameterMap.put("o", search);
		modelMap.put("page", operatorService.findOperator(parameterMap));
		modelMap.put("o", search);
		return "page/operator/list";
	}

	@RequestMapping(value = "add_view", method = RequestMethod.POST)
	public String add_view( ModelMap modelMap) {
		List<RoleBean> list = roleService .getRoleListForAuthorizeByEnabledAndOperatorId(null);
		modelMap.put("list", list);
		return "page/operator/add";
	}
	
	@RequestMapping(value = "edit_view", method = RequestMethod.POST)
	public String edit_view(Integer id, ModelMap modelMap) {
		List<RoleBean> list = roleService .getRoleListForAuthorizeByEnabledAndOperatorId(id);
		modelMap.put("list", list);
		//modelMap.put("o", operatorService.getOperatorById(id));
		return "page/operator/edit";
	}
	
	@RequestMapping(value = "profileEdit_view", method = RequestMethod.POST)
	@MethodLog(remark = "转跳个人信息修改页面")
	public String edit_view(ModelMap modelMap) {
		//modelMap.put("o", operatorService.getOperatorById(SecurityUtils.getCurrentOperator().getId()));
		return "page/operator/profileEdit";
	}
	
	
	
	
	@RequestMapping(value = "view", method = RequestMethod.POST)
	public String view(Integer id, ModelMap modelMap) {
		List<RoleBean> list = roleService .getRoleListForAuthorizeByEnabledAndOperatorId(id);
		modelMap.put("list", list);
		//modelMap.put("o", operatorService.getOperatorById(id));
		return "page/operator/view";
	}



	@RequestMapping(value = "addOperator", method = RequestMethod.POST)
	@ResponseBody
	@MethodLog(remark = "添加用户(含头像)")
	public MsgUtil addOperator(Operator o,String roleIds, HttpServletRequest request,
			Integer x, Integer y, Integer w, Integer h) throws IOException {
		return operatorService.addOperator(o, roleIds,request, x, y, w, h);
	}

	@RequestMapping(value = "updateOperatorById", method = RequestMethod.POST)
	@MethodLog(remark = "修改用户(含头像)")
	@ResponseBody
	public MsgUtil updateOperatorById(HttpServletResponse response,Operator o,String roleIds, HttpServletRequest request,
			Integer x, Integer y, Integer w, Integer h)throws IOException {
		return  operatorService.updateOperatorById(o, roleIds, request, x, y, w, h);
	}
	
	@RequestMapping(value = "updateProfileById", method = RequestMethod.POST)
	@MethodLog(remark = "修改个人信息")
	@ResponseBody
	public MsgUtil updateProfileById(Operator o)throws IOException {
		return operatorService.updateProfileById(o);
	}

	@RequestMapping(value = "updatePasswordById", method = RequestMethod.POST)
	@ResponseBody
	public MsgUtil updatePasswordById(String password) {
		return operatorService.updatePasswordById(password);
	}

	@RequestMapping(value = "changeEnabled", method = RequestMethod.POST)
	@ResponseBody
	@MethodLog(remark = "改变用户状态")
	public MsgUtil changeEnabled(Integer id, Boolean flg) {
		return operatorService.changeEnabled(id, flg);
	}

	@RequestMapping(value = "checkLoginNameRepeat", method = RequestMethod.POST)
	@ResponseBody
	@MethodLog(remark = "校验用户名是否重复")
	public MsgUtil checkLoginNameRepeat(String loginName) {
		return operatorService.checkLoginNameRepeat(loginName);
	}
	
	@RequestMapping(value = "checkLoginNameRepeatNoAuth", method = RequestMethod.POST)
	@ResponseBody
	@MethodLog(remark = "校验用户名是否重复")
	public MsgUtil checkLoginNameRepeatNoAuth(String loginName) {
		return operatorService.checkLoginNameRepeat(loginName);
	}

	@RequestMapping(value = "checkSelfPasswordById", method = RequestMethod.POST)
	@ResponseBody
	@MethodLog(remark = "修改用户密码时校验原密码")
	public MsgUtil checkSelfPasswordById(String password) {
		return operatorService.checkSelfPasswordById(password);
	}

	@RequestMapping(value = "resetPassword", method = RequestMethod.POST)
	@ResponseBody
	public MsgUtil resetPassword(Operator o) {
		return operatorService.resetPassword(o);
	}

}
