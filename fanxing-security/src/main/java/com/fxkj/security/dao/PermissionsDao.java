package com.fxkj.security.dao;

import java.util.List;
import java.util.Map;

import com.fxkj.security.entity.Permissions;

public interface PermissionsDao {

	/**
	 * 添加权限
	 * 
	 * @param p
	 * @return
	 */
	public Integer addPerssions(Permissions p);

	/**
	 * 检索出所有可用的权限，用于内存权限缓存
	 * 
	 * @param
	 * @return
	 */
	public List<Permissions> getPermissionsForRedisAuthorizeByEnabled(Integer flg);

	/**
	 * 获取要授权的模块(已授权的进行选中)
	 * @param map
	 * @return
	 */
	public List<Permissions> getPermissionsEnabledForAuthorize(Map<String, Object> map);



	/**
	 * 根据角色id集合检索出所对应的权限集合 不包括按钮(可用的) 
	 * 
	 * @param ids
	 * @return
	 */
	public List<Permissions> getPermissionListByRoleIds(String ids);

	/**
	 * 获取实体（一些字段）
	 * 
	 * @param id
	 * @return
	 */
	public Permissions getPermissionsForSimpleById(Integer id);

}
