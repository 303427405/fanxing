package com.fxkj.security.bean;


public class PermissionsBean {
	private Integer id;
	private Integer parentId;
	private String icon;
	private String webUrl;
	private String btnFlg;

	private Integer leaf;
	private Integer type;
	private Integer enabled;
	private String name;
	
	private Boolean checked;
	
	private Boolean chkDisabled=false;
	private Boolean open=false; 

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


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

}
