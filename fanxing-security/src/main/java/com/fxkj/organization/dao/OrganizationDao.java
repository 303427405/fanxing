package com.fxkj.organization.dao;

import java.util.List;
import java.util.Map;

import com.fxkj.core.base.PageInfo;
import com.fxkj.organization.entity.Organization;

public interface OrganizationDao {

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
	public Integer addOrganization(Organization o);

	/**
	 * 加载实体
	 * 
	 * @param id
	 * @return
	 */

	public Organization getOrganizationById(Integer id);

	/**
	 * 根据区域加载组织
	 * 
	 * @param areaCode
	 * @return
	 */
	public List<Organization> getOrganizationByAreaCode(String areaCode);

	/**
	 * 根据编码加载组织
	 * 
	 * @param orgCode
	 * @return
	 */
	public Organization getOrganizationByCode(String orgCode);

	/**
	 * 修改组织
	 * 
	 * @param o
	 * @return
	 */
	public Integer updateOrganizationById(Organization o);

	/**
	 * 校验所在区域的code是否重复
	 * 
	 * @param parameterMap
	 * @return
	 */
	public List<Integer> checkCodeRepeatByAreaCode(
            Map<String, Object> parameterMap);

	/**
	 * 改变组织状态
	 * 
	 * @param map
	 * @return
	 */
	public Integer changeEnabled(Map<String, Object> map);

}
