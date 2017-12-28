package com.fxkj.area.dao;

import java.util.List;
import java.util.Map;

import com.fxkj.area.entity.Area;

public interface AreaDao {

	/**
	 * 查询区域
	 * 
	 * @param parameterMap
	 * @return
	 */
	public List<Area> findArea(Map<String, Object> parameterMap);

	/**
	 * 根据名称或编码搜索区域【下拉框】
	 * @param
	 * @return
	 */
	public List<Area> searchAreaByNameOrCode(String nameOrCode);
	
	/**
	 * 根据区域编码加载实体
	 * @param code
	 * @return
	 */
	public Area getAreaByCode(String code);
	
	
}
