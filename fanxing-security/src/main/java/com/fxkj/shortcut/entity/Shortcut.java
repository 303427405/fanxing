package com.fxkj.shortcut.entity;

import com.fxkj.core.base.BaseEntity;

@SuppressWarnings("serial")
public class Shortcut extends BaseEntity {

	private Integer id;
	private String name;
	private Integer operatorId;
	private Integer permissionsId;
	private Integer sequence;
	private String bgColor;
	private String webUrl;
	private String icon;

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

	public String getBgColor() {
		return bgColor;
	}

	public void setBgColor(String bgColor) {
		this.bgColor = bgColor;
	}

	public Integer getId() {
		return id;
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

	public Integer getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(Integer operatorId) {
		this.operatorId = operatorId;
	}

	public Integer getPermissionsId() {
		return permissionsId;
	}

	public void setPermissionsId(Integer permissionsId) {
		this.permissionsId = permissionsId;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

}
