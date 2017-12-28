package com.fxkj.area.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.fxkj.area.dao.AreaDao;
import com.fxkj.area.entity.Area;
import com.fxkj.core.base.BaseDaoImpl;

@Repository("areaDao")
@SuppressWarnings("unchecked")
public class AreaDaoImpl extends BaseDaoImpl<Area> implements AreaDao {

	public List<Area> findArea(Map<String, Object> parameterMap) {
		return (List<Area>) getSqlMapClientTemplate().queryForList(
				"area.listArea", parameterMap);
	}

	public List<Area> searchAreaByNameOrCode(String nameOrCode) {
		return (List<Area>) getSqlMapClientTemplate().queryForList(
				"area.searchAreaByNameOrCode", nameOrCode);
	}

	public Area getAreaByCode(String code) {
		return (Area) getSqlMapClientTemplate().queryForObject(
				"area.getAreaByCode", code);
	}
}
