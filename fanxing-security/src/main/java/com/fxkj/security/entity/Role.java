package com.fxkj.security.entity;

import com.fxkj.core.base.BaseEntity;

/**
 * 角色
 * 
 * 
 */
@SuppressWarnings("serial")
public class Role extends BaseEntity {
	private Integer id;
	private String roleName;
	private String description;
	private Integer enabled;

	private Integer checked;
	private String permissionIds;

	public String getPermissionIds() {
		return permissionIds;
	}

	public void setPermissionIds(String permissionIds) {
		this.permissionIds = permissionIds;
	}

	public Integer getChecked() {
		return checked;
	}

	public void setChecked(Integer checked) {
		this.checked = checked;
	}


	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
