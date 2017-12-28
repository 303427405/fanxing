package com.fxkj.shortcut.dao;

import java.util.List;
import java.util.Map;

import com.fxkj.core.base.PageInfo;
import com.fxkj.security.entity.Permissions;
import com.fxkj.shortcut.entity.Shortcut;

public interface ShortcutDao {
	
	/**
	 * 当前用户快捷方式查询 (权限可用)
	 * @param parameterMap
	 * @return
	 */
	public PageInfo<Shortcut> findShortcut(Map<String, Object> parameterMap);
	
	
	/**
	 * 获取当前用户所拥有的桌面快捷方式 (权限可用)
	 * @param operatorId
	 * @return
	 */
	public List<Shortcut> getShortcutByCurrentOperator(Integer operatorId);
	
	

	/**
	 * 添加用户的快捷方式
	 * 
	 * @param s
	 * @return
	 */
	public Integer addShortcut(Shortcut s);

	/**
	 * 删除用户的快捷方式
	 * 
	 * @param id
	 * @return
	 */
	public Integer deleteShortcut(Integer id);
	
	/**
	 * 获取用户创建的快捷方式的总数
	 * @param operatorId
	 * @return
	 */
	public Integer getShortcutCountByOperatorId(Integer operatorId);
	
	
	
	
	/**
	 * 获取当前用户能创建快捷方式的模块(已创建的进行选中,权限可用,角色可用)
	 * @param map
	 * @return
	 */
	public List<Permissions> getOperatorPermissionsForShortcut(Integer operatorId);

}
