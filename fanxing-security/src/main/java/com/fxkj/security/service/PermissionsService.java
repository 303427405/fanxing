package com.fxkj.security.service;

import java.util.List;

import com.fxkj.security.bean.PermissionsBean;
import com.fxkj.security.entity.Permissions;

public interface PermissionsService {

	/**
	 * 添加权限
	 * 
	 * @param p
	 * @return
	 */
    Integer addPerssions(Permissions p);

	/**
	 * 获取当前用户所有权限，不包括按钮，角色可用，权限可用 ，用于页面菜单渲染
	 * 
	 * @return
	 */
    List<PermissionsBean> getPermissionsByCurrentOperator();


	/**
	 * 获取系统能授权的权限，并标识角色已授的权限
	 * @param roleId
	 * @param flg
	  * @param disabled 是否禁用所有节点
	 * @return
	 */
    List<PermissionsBean> getPermissionsForAuth(Integer roleId, Integer flg, Boolean disabled);

}
