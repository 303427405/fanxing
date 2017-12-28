package com.fxkj.security.bean;

import com.fxkj.core.base.BaseSearch;
import com.fxkj.core.utils.StringUtil;

public class OperatorSearch extends BaseSearch{
	private String loginName;
	private Integer enabled;// 状态 1：启用 0：禁用
	private String orgCode;
	private String areaCodes;
	private String areaCode;

	public String getAreaCode() {
		if (StringUtil.isNotEmpty(areaCodes)) {
			String[] code = areaCodes.split(",");
			return code[code.length - 1];
		}
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getAreaCodes() {
		return areaCodes;
	}

	public void setAreaCodes(String areaCodes) {
		this.areaCodes = areaCodes;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getLoginName() {
		return StringUtil.getEscapeString(loginName);
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}



}
