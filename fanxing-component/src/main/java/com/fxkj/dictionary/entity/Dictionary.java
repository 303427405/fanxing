package com.fxkj.dictionary.entity;

import com.fxkj.core.base.BaseEntity;
import com.fxkj.core.base.NoCheckLogin;
@SuppressWarnings("serial")
@NoCheckLogin
public class Dictionary extends BaseEntity {
	private Integer id;
	private String name;
	private String code;// option 值 String
	private Integer parentId; // 父id
	private Integer leaf;// 层级
	private Integer enabled;// 状态 1：启用 0：禁用
	private Integer  sequence;

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public Integer getId() {
		return id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
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

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}


}
