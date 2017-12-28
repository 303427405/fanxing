package com.fxkj.shortcut.service;

import java.util.List;
import java.util.Map;

import com.fxkj.core.base.MsgUtil;
import com.fxkj.core.base.PageInfo;
import com.fxkj.security.bean.PermissionsBean;
import com.fxkj.shortcut.bean.ShortcutBean;
import com.fxkj.shortcut.entity.Shortcut;

public interface ShortcutService {

	/**
	 * 快捷方式列表
	 * 
	 * @param parameterMap
	 * @return
	 */
	public PageInfo<Shortcut> findShortcut(Map<String, Object> parameterMap);

	/**
	 * 获取当前用户所拥有的桌面快捷方式 (权限可用)
	 * 
	 * @return
	 */

	public List<ShortcutBean> getShortcutByCurrentOperator();

	/**
	 * 获取当前用户能创建快捷方式的模块(已创建的进行选中,权限可用,角色可用)
	 * 
	 * @param operatorId
	 * @return
	 */
	public List<PermissionsBean> getOperatorPermissionsForShortcut(
            Integer operatorId);

	/**
	 *  添加当前用户所拥有的快捷方式
	 * @param s
	 * @return
	 */
	public MsgUtil addShortcut(Shortcut s);

	/**
	 * 删除当前用户所拥有的快捷方式
	 * @param ids
	 * @return
	 */
	public MsgUtil delShortcut(String ids);

}
