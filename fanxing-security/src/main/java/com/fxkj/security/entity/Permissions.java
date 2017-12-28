package com.fxkj.security.entity;

import com.fxkj.core.base.BaseEntity;
import com.fxkj.core.base.NoCheckLogin;

@SuppressWarnings("serial")
@NoCheckLogin
public class Permissions extends BaseEntity {

	private Integer parentId;
	private String icon;
	private String webUrl;
	private String btnFlg;

	private Integer leaf;
	private Integer type;
	private Integer enabled;
	private String name;
	private String namePath;
	// 快捷方式背景色
	private String bgColor;
	private Boolean checked;

	private Boolean chkDisabled = false;//是否禁用选择框
	private Boolean open=false; //是否打开

	public Boolean getOpen() {
		return open;
	}

	public void setOpen(Boolean open) {
		this.open = open;
	}

	public Boolean getChkDisabled() {
		return chkDisabled;
	}

	public void setChkDisabled(Boolean chkDisabled) {
		this.chkDisabled = chkDisabled;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public String getBgColor() {
		return bgColor;
	}

	public void setBgColor(String bgColor) {
		this.bgColor = bgColor;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getWebUrl() {
		return webUrl;
	}

	public void setWebUrl(String webUrl) {
		this.webUrl = webUrl;
	}

	public String getBtnFlg() {
		return btnFlg;
	}

	public void setBtnFlg(String btnFlg) {
		this.btnFlg = btnFlg;
	}

	public Integer getLeaf() {
		return leaf;
	}

	public void setLeaf(Integer leaf) {
		this.leaf = leaf;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNamePath() {
		return namePath;
	}

	public void setNamePath(String namePath) {
		this.namePath = namePath;
	}

}
