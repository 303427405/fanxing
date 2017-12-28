package com.fxkj.area.service;

import java.util.List;

import com.fxkj.area.bean.AreaBean;
import com.fxkj.area.bean.AreaSearchBean;
import com.fxkj.area.entity.Area;

public interface AreaService {

	/**
	 * 查询区域
	 * @param bean
	 * @return
	 */
	public List<AreaBean> findArea(AreaSearchBean bean);
	
	/**
	 * 根据名称或编码搜索区域【下拉框】
	 * @param nameOrCode
	 * @return
	 */
	public List<AreaBean> searchAreaByNameOrCode(String nameOrCode);
	
	
	/**
	 * 根据区域编码加载实体
	 * @param code
	 * @return
	 */
	public Area getAreaByCode(String code);

}
