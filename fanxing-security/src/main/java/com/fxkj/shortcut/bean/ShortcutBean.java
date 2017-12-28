package com.fxkj.shortcut.bean;

public class ShortcutBean {
	private Integer id;
	private String name;
	private String bgColor;
	private Integer sequence;
	private String webUrl;
	private String icon;
	private Integer permissionsId;

	public Integer getPermissionsId() {
		return permissionsId;
	}

	public void setPermissionsId(Integer permissionsId) {
		this.permissionsId = permissionsId;
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

	public String getBgColor() {
		return bgColor;
	}

	public void setBgColor(String bgColor) {
		this.bgColor = bgColor;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

}
