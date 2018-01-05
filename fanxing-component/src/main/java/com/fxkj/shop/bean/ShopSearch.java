package com.fxkj.shop.bean;


import com.fxkj.core.base.BaseSearch;
import com.fxkj.core.utils.StringUtil;

public class ShopSearch extends BaseSearch {

	private String orgCode;
	private String areaCodes;
	private String areaCode;
	private Integer status;

	private String name;
	private Integer leaf;
	private Integer parentId;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

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

}
