package com.fxkj.organization.controller;

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
import com.fxkj.core.base.MsgUtil;
import com.fxkj.core.utils.StringUtil;
import com.fxkj.organization.bean.OrganizationSearch;
import com.fxkj.organization.entity.Organization;
import com.fxkj.organization.service.OrganizationService;

@Controller
@RequestMapping("/organizationController")
public class OrganizationController extends BaseAction {

	@Autowired
	private OrganizationService organizationService;

	@RequestMapping(value = "findOrganization")
	public String findOrganization(OrganizationSearch search, String areaCodes,String orgCode,
			HttpServletRequest request, ModelMap modelMap) {
		Map<String, Object> parameterMap = getGridParam(request);
		if (StringUtil.isNotEmpty(areaCodes)) {
			String[] code = areaCodes.split(",");
			search.setAreaCode(code[code.length - 1]);
		}
		parameterMap.put("org", search);
		modelMap.put("page", organizationService.findOrganization(parameterMap));
		modelMap.put("o", search);
		modelMap.put("areaCodes", areaCodes);
		modelMap.put("orgCode", orgCode);
		return "page/organization/list";
	}

	@RequestMapping(value = "add_view", method = RequestMethod.POST)
	public String add_view(ModelMap modelMap) {
		return "page/organization/add";
	}

	@RequestMapping(value = "edit_view", method = RequestMethod.POST)
	public String edit_view(Integer id, ModelMap modelMap) {
		modelMap.put("o", organizationService.getOrganizationById(id));
		return "page/organization/edit";
	}

	@RequestMapping(value = "view", method = RequestMethod.POST)
	public String view(Integer id, ModelMap modelMap) {
		modelMap.put("o", organizationService.getOrganizationById(id));
		return "page/organization/view";
	}

	@RequestMapping(value = "addOrganization", method = RequestMethod.POST)
	@ResponseBody
	public MsgUtil addOrganization(Organization o) {
		return organizationService.addOrganization(o);
	}

	@RequestMapping(value = "updateOrganizationById", method = RequestMethod.POST)
	@ResponseBody
	public MsgUtil updateOrganizationById(Organization o) {
		return organizationService.updateOrganizationById(o);
	}

	@RequestMapping(value = "getOrganizationByAreaCode", method = RequestMethod.POST)
	@ResponseBody
	public List<Organization> getOrganizationByAreaCode(String areaCode) {
		return organizationService.getOrganizationByAreaCode(areaCode);
	}

	@RequestMapping(value = "changeEnabled", method = RequestMethod.POST)
	@ResponseBody
	public MsgUtil changeEnabled(Integer id, Boolean flg) {
		return organizationService.changeEnabled(id, flg);
	}

}
