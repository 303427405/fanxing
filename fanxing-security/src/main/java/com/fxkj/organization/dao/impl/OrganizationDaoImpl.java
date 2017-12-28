package com.fxkj.organization.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.fxkj.core.base.BaseDaoImpl;
import com.fxkj.core.base.PageInfo;
import com.fxkj.organization.dao.OrganizationDao;
import com.fxkj.organization.entity.Organization;

@Repository("organizationDao")
@SuppressWarnings("unchecked")
public class OrganizationDaoImpl extends BaseDaoImpl<Organization> implements
		OrganizationDao {

	public PageInfo<Organization> findOrganization(
			Map<String, Object> parameterMap) {
		return getPageByParamMap(new PageInfo<Organization>(),
				"organization.countOrg", "organization.listOrg", parameterMap);
	}

	public Integer addOrganization(Organization o) {
		return (Integer) getSqlMapClientTemplate().insert(
				"organization.addOrganization", o);

	}
	public Organization getOrganizationById(Integer id) {
		return (Organization) getSqlMapClientTemplate().queryForObject(
				"organization.getOrganizationById", id);
	}

	public List<Organization> getOrganizationByAreaCode(String areaCode) {
		return (List<Organization>) getSqlMapClientTemplate().queryForList(
				"organization.getOrganizationByAreaCode", areaCode);
	}
	
	public Organization getOrganizationByCode(String orgCode){
		return (Organization) getSqlMapClientTemplate().queryForObject(
				"organization.getOrganizationByCode", orgCode);
	}

	public List<Integer> checkCodeRepeatByAreaCode(
			Map<String, Object> parameterMap) {
		return (List<Integer>) getSqlMapClientTemplate().queryForList(
				"organization.checkCodeRepeatByAreaCode", parameterMap);
	}

	public Integer changeEnabled(Map<String, Object> map) {
		return (Integer) getSqlMapClientTemplate().update(
				"organization.changeEnabled", map);
	}

	public Integer updateOrganizationById(Organization o) {
		return (Integer) getSqlMapClientTemplate().update(
				"organization.updateOrganizationById", o);
	}

}
