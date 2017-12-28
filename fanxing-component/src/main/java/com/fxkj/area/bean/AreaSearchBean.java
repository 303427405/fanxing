package com.fxkj.area.bean;

import com.fxkj.core.base.BaseSearch;
import com.fxkj.core.utils.StringUtil;

public class AreaSearchBean extends BaseSearch{
	private Integer parentId;
	private String nameOrCode;

	public String getNameOrCode() {
		return StringUtil.getEscapeString(nameOrCode);
	}

	public void setNameOrCode(String nameOrCode) {
		this.nameOrCode = nameOrCode;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

}
