package com.fxkj.security.dao;

import java.util.List;
import java.util.Map;

import com.fxkj.core.base.PageInfo;
import com.fxkj.security.entity.Role;
import com.fxkj.security.entity.RoleAndOperator;
import com.fxkj.security.entity.RoleAndPermissions;

public interface RoleDao {

	/**
	 * 角色列表
	 * 
	 * @param parameterMap
	 * @return
	 */
    PageInfo<Role> findRole(Map<String, Object> parameterMap);

	/**
	 * 添加角色
	 * 
	 * @param r
	 * @return
	 */
    Integer addRole(Role r);

	/**
	 * 添加角色与权限的对应关系
	 * 
	 * @param rp
	 * @return
	 */
    Integer addRoleAndPermissions(RoleAndPermissions rp);
	
	/**
	 * 添加用户与角色对应关系
	 * @param ro
	 * @return
	 */
    Integer addRoleAndOperator(RoleAndOperator ro) ;
	
	
	/**
	 * 根据id检索实体
	 * @param id
	 * @return
	 */
    Role getRoleById(Integer id);

	/**
	 * 删除角色对应的权限
	 * 
	 * @param roleId
	 * @return
	 */
    Integer deletePermissionsListByRoleId(Integer roleId);
	
	/**
	 * 删除用户与角色对应关系
	 * @param OperatorId
	 * @return
	 */
    Integer deleteRoleByOperatorId(Integer OperatorId) ;

	/**
	 * 修改角色
	 * 
	 * @param r
	 * @return
	 */
    Integer updateRoleById(Role r);

	/**
	 * 改变角色状态
	 * 
	 * @param map
	 * @return
	 */
    Integer changeEnabled(Map<String, Object> map);

	/**
	 * 根据登录用户检索所拥有的角色集合 (角色可用)
	 * 
	 * @param operatorId
	 * @return
	 */
    List<Role> getEnabledRoleListByOperatorId(Integer operatorId);

	/**
	 * 根据权限检索出所对应的角色集合 (角色 可用,已授权)用于内存权限缓存
	 * 
	 * @param pId
	 * @return
	 */
    List<Role> getRoleListForRedisAuthorizeByPId(Integer pId);


	
	/**
	 * 查询所有可用角色，并标识用户已授权的角色
	 * @param map
	 * @return
	 */
    List<Role> getRoleListForAuthorizeByEnabledAndOperatorId(Map<String, Object> map);

}
