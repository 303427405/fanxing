package com.fxkj.security.service;

import java.util.List;
import java.util.Map;

import com.fxkj.core.base.MsgUtil;
import com.fxkj.core.base.PageInfo;
import com.fxkj.security.bean.RoleBean;
import com.fxkj.security.entity.Role;

public interface RoleService {

	/**
	 * 角色列表
	 * 
	 * @param parameterMap
	 * @return
	 */
	public PageInfo<Role> findRole(Map<String, Object> parameterMap);

	/**
	 * 添加角色
	 * 
	 * @param r
	 * @return
	 */
	public MsgUtil addRole(Role r);
	
	/**
	 * 根据id检索实体
	 * @param id
	 * @return
	 */
	public Role getRoleById(Integer id);
	
	/**
	 * 修改角色初始化数据
	 * @param id
	 * @return
	 */
	public Role edit_view(Integer id);


	/**
	 * 修改角色
	 * 
	 * @param r
	 * @return
	 */
	public MsgUtil updateRoleById(Role r);

	/**
	 * 改变角色状态
	 * 
	 * @param id
	 * @param flg
	 * @return
	 */
	public MsgUtil changeEnabled(Integer id, Boolean flg);

	/**
	 * 查询所有可用角色，并标识用户已授权的角色
	 * 
	 * @param operatorId
	 * @return
	 */
	public List<RoleBean> getRoleListForAuthorizeByEnabledAndOperatorId(
            Integer operatorId);
	
	public List<Role> getEnabledRoleListByOperatorId(Integer operatorId);

}
