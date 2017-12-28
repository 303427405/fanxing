package com.fxkj.organization.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fxkj.core.base.BaseService;
import com.fxkj.core.base.MsgUtil;
import com.fxkj.core.base.PageInfo;
import com.fxkj.core.utils.DateUtils;
import com.fxkj.enums.EnabledEnum;
import com.fxkj.organization.dao.OrganizationDao;
import com.fxkj.organization.entity.Organization;
import com.fxkj.organization.service.OrganizationService;

@Service("organizationService")
@Transactional
public class OrganizationServiceImpl extends BaseService implements
		OrganizationService {
	@Autowired
	private OrganizationDao organizationDao;

	@Override
	public PageInfo<Organization> findOrganization(
			Map<String, Object> parameterMap) {
		return organizationDao.findOrganization(parameterMap);
	}

	@Override
	public MsgUtil addOrganization(Organization o) {
		if (o != null && o.getAreaCode() != null) {
			o.setCode(o.getAreaCode()+"_"+DateUtils.longDate());
			o.setState(EnabledEnum.ENABLED.getCode());
			int temp = organizationDao.addOrganization(o);
			return new MsgUtil(temp > 0 ? true : false, temp > 0 ? SUCCESS_MSG
					: ERROR_MSG);
		} else {
			return new MsgUtil(false, PARAM_MSG);
		}

	}

	@Override
	public MsgUtil updateOrganizationById(Organization o) {
		if (o.getId() != null) {
			int temp = organizationDao.updateOrganizationById(o);
			return new MsgUtil(temp > 0 ? true : false, temp > 0 ? SUCCESS_MSG
					: ERROR_MSG);
		} else {
			return new MsgUtil(false, PARAM_MSG);
		}
	}

	@Override
	public Organization getOrganizationById(Integer id) {
		return organizationDao.getOrganizationById(id);
	}


	@Override
	public List<Organization> getOrganizationByAreaCode(String areaCode) {
		return organizationDao.getOrganizationByAreaCode(areaCode);
	}

	@Override
	public MsgUtil changeEnabled(Integer id, Boolean flg) {
		if (id != null && flg != null) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", id);
			map.put("flg", flg);
			int temp = organizationDao.changeEnabled(map);
			return new MsgUtil(temp > 0 ? true : false, temp > 0 ? SUCCESS_MSG
					: ERROR_MSG);
		} else {
			return new MsgUtil(false, PARAM_MSG);
		}
	}

}
