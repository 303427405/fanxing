package com.fxkj.organization.service;

import java.util.List;
import java.util.Map;

import com.fxkj.core.base.MsgUtil;
import com.fxkj.core.base.PageInfo;
import com.fxkj.organization.entity.Organization;

public interface OrganizationService {
	/**
	 * 组织列表
	 * 
	 * @param parameterMap
	 * @return
	 */
	public PageInfo<Organization> findOrganization(
            Map<String, Object> parameterMap);

	/**
	 * 添加组织
	 * 
	 * @param o
	 * @return
	 */
	public MsgUtil addOrganization(Organization o);

	/**
	 * 修改组织
	 * 
	 * @param o
	 * @return
	 */
	public MsgUtil updateOrganizationById(Organization o);

	/**
	 * 加载实体
	 * 
	 * @param id
	 * @return
	 */
	public Organization getOrganizationById(Integer id);
	
	/**
	 * 根据区域加载组织
	 * @param areaCode
	 * @return
	 */
	public List<Organization> getOrganizationByAreaCode(String areaCode);

	/**
	 * 改变组织项状态
	 * 
	 * @param id
	 * @param flg
	 * @return
	 */
	public MsgUtil changeEnabled(Integer id, Boolean flg);

}
