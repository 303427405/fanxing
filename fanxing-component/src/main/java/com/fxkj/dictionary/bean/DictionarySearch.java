package com.fxkj.dictionary.bean;

import com.fxkj.core.base.BaseSearch;
import com.fxkj.core.utils.StringUtil;

public class DictionarySearch extends BaseSearch{
	private String name;
	private Integer parentId;
	private Integer leaf;
	private Integer enabled;// 状态 1：启用 0：禁用

	

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	public String getName() {
		return StringUtil.getEscapeString(name);
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getLeaf() {
		return leaf;
	}

	public void setLeaf(Integer leaf) {
		this.leaf = leaf;
	}

}
