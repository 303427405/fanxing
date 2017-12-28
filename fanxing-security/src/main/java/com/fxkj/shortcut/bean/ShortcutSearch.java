package com.fxkj.shortcut.bean;

import com.fxkj.core.base.BaseSearch;

public class ShortcutSearch extends BaseSearch {

	private String name;
	private Integer operatorId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(Integer operatorId) {
		this.operatorId = operatorId;
	}
}
