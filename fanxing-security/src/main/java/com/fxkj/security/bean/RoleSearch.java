package com.fxkj.security.bean;

import com.fxkj.core.base.BaseSearch;
import com.fxkj.core.utils.StringUtil;

public class RoleSearch extends BaseSearch{
	private String roleName;
	private Integer enabled;


	public String getRoleName() {
		return StringUtil.getEscapeString(roleName);
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}


}
