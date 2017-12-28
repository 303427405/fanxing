package com.fxkj.organization.bean;

import com.fxkj.core.base.BaseSearch;
import com.fxkj.core.utils.StringUtil;

public class OrganizationSearch extends BaseSearch{

	private String name;
	private String code;
	private Integer state;
	private String areaCode;

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	

	public String getName() {
		return StringUtil.getEscapeString(name);
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return StringUtil.getEscapeString(code);
	}

	public void setCode(String code) {
		this.code = code;
	}

}
