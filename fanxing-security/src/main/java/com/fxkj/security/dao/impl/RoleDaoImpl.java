package com.fxkj.security.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.fxkj.core.base.BaseDaoImpl;
import com.fxkj.core.base.PageInfo;
import com.fxkj.security.dao.RoleDao;
import com.fxkj.security.entity.Role;
import com.fxkj.security.entity.RoleAndOperator;
import com.fxkj.security.entity.RoleAndPermissions;

@Repository("roleDao")
@SuppressWarnings("unchecked")
public class RoleDaoImpl extends BaseDaoImpl<Role> implements RoleDao {

	public PageInfo<Role> findRole(Map<String, Object> parameterMap) {
		return getPageByParamMap(new PageInfo<Role>(), "role.countRole",
				"role.listRole", parameterMap);
	}

	public Integer changeEnabled(Map<String, Object> map) {
		return (Integer) getSqlMapClientTemplate().update("role.changeEnabled",
				map);
	}

	public Integer addRole(Role r) {
		return (Integer) getSqlMapClientTemplate().insert("role.addRole", r);
	}
	public Integer addRoleAndPermissions(RoleAndPermissions rp) {
		return (Integer) getSqlMapClientTemplate().insert("role.addRoleAndPermissions", rp);
	}

	public Integer addRoleAndOperator(RoleAndOperator ro) {
		return (Integer) getSqlMapClientTemplate().insert("role.addRoleAndOperator", ro);
	}

	public Integer deletePermissionsListByRoleId(Integer roleId) {
		return (Integer) getSqlMapClientTemplate().delete("role.deletePermissionsListByRoleId", roleId);
	}

	public Integer deleteRoleByOperatorId(Integer OperatorId){
		return (Integer) getSqlMapClientTemplate().delete("role.deleteRoleByOperatorId", OperatorId);
	}

	public Integer updateRoleById(Role r) {
		return (Integer) getSqlMapClientTemplate().update(
				"role.updateRoleById", r);
	}

	public List<Role> getEnabledRoleListByOperatorId(Integer operatorId) {
		return (List<Role>) getSqlMapClientTemplate().queryForList(
				"role.getEnabledRoleListByOperatorId", operatorId);
	}

	public List<Role> getRoleListForRedisAuthorizeByPId(Integer pId) {
		return (List<Role>) getSqlMapClientTemplate().queryForList(
				"role.getRoleListForRedisAuthorizeByPId",pId);
	}


    public Role getRoleById(Integer id) {
		return (Role)getSqlMapClientTemplate().queryForObject("role.getRoleById", id);
	}

	public List<Role> getRoleListForAuthorizeByEnabledAndOperatorId(Map<String, Object> map) {
		return (List<Role>) getSqlMapClientTemplate().queryForList(
				"role.getRoleListForAuthorizeByEnabledAndOperatorId", map);
	}

}
