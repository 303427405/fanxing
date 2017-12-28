package com.fxkj.shortcut.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.fxkj.core.base.BaseDaoImpl;
import com.fxkj.core.base.PageInfo;
import com.fxkj.security.entity.Permissions;
import com.fxkj.shortcut.dao.ShortcutDao;
import com.fxkj.shortcut.entity.Shortcut;

@Repository("shortcutDao")
@SuppressWarnings("unchecked")
public class ShortcutDaoImpl extends BaseDaoImpl<Shortcut> implements
		ShortcutDao {

	public PageInfo<Shortcut> findShortcut(Map<String, Object> parameterMap) {
		return getPageByParamMap(new PageInfo<Shortcut>(),
				"shortcut.countShortcut", "shortcut.listShortcut", parameterMap);
	}

	public List<Shortcut> getShortcutByCurrentOperator(Integer operatorId) {
		return (List<Shortcut>) getSqlMapClientTemplate().queryForList(
				"shortcut.getShortcutByCurrentOperator", operatorId);
	}

	public Integer getShortcutCountByOperatorId(Integer operatorId) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"shortcut.getShortcutCountByOperatorId", operatorId);
	}

	public Integer addShortcut(Shortcut s) {
		return (Integer) getSqlMapClientTemplate().insert(
				"shortcut.addShortcut", s);

	}

	public Integer deleteShortcut(Integer id) {
		return (Integer) getSqlMapClientTemplate().delete(
				"shortcut.deleteShortcut", id);
	}

	public List<Permissions> getOperatorPermissionsForShortcut(
			Integer operatorId) {
		return (List<Permissions>) getSqlMapClientTemplate().queryForList(
				"shortcut.getOperatorPermissionsForShortcut", operatorId);
	}
}
