package com.fxkj.security.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.fxkj.core.base.BaseDaoImpl;
import com.fxkj.security.dao.PermissionsDao;
import com.fxkj.security.entity.Permissions;

@Repository("permissionsDao")
@SuppressWarnings("unchecked")
public class PermissionsDaoImpl extends BaseDaoImpl<Permissions> implements
		PermissionsDao {

	public Integer addPerssions(Permissions p) {
		return (Integer) getSqlMapClientTemplate().insert(
				"permission.addPermissions", p);
	}

	public List<Permissions> getPermissionsForRedisAuthorizeByEnabled(Integer flg) {
		return (List<Permissions>) getSqlMapClientTemplate().queryForList(
				"permission.getPermissionsForRedisAuthorizeByEnabled", flg);
	}

	public List<Permissions> getPermissionsEnabledForAuthorize(Map<String, Object> map) {
		return (List<Permissions>) getSqlMapClientTemplate().queryForList(
				"permission.getPermissionsEnabledForAuthorize", map);
	}

	public List<Permissions> getPermissionListByRoleIds(String ids) {
		return (List<Permissions>) getSqlMapClientTemplate().queryForList(
				"permission.getPermissionListByRoleIds", ids);
	}


	public Permissions getPermissionsForSimpleById(Integer id) {
		return (Permissions) getSqlMapClientTemplate().queryForObject(
				"permission.getPermissionsForSimpleById", id);
	}

}
