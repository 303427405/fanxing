package com.fxkj.security.entity;

public class RoleAndPermissions {
	private Integer id;
	private Integer roleId;
	private Integer permissionsId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public RoleAndPermissions() {
	}

	public RoleAndPermissions(Integer rId, Integer pId) {
		this.roleId = rId;
		this.permissionsId = pId;
	}

	public Integer getPermissionsId() {
		return permissionsId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setPermissionsId(Integer permissionsId) {
		this.permissionsId = permissionsId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
}
