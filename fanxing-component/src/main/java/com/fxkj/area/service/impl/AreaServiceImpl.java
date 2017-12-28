package com.fxkj.area.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fxkj.area.bean.AreaBean;
import com.fxkj.area.bean.AreaSearchBean;
import com.fxkj.area.dao.AreaDao;
import com.fxkj.area.entity.Area;
import com.fxkj.area.service.AreaService;
import com.fxkj.core.base.BaseService;
import com.fxkj.core.utils.BeanUtil;
import com.fxkj.core.utils.StringUtil;

@Service("areaService")
public class AreaServiceImpl extends BaseService implements AreaService {
	@Autowired
	private AreaDao areaDao;

	public List<AreaBean> findArea(AreaSearchBean bean) {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		if (StringUtil.isNotEmpty(bean.getNameOrCode())) {
			parameterMap.put("nameOrCode", bean.getNameOrCode());
		} else {
			parameterMap.put("flg", true);
			parameterMap.put("parentId", bean.getParentId());
		}
		List<Area> list = areaDao.findArea(parameterMap);
		for (Area a : list) {
			String icon = "";
			if (a.getLevel() == 1) {
				icon = "icon-guoqiflag49";
			} else if (a.getLevel() == 2) {
				icon = "icon-shengfen";
			} else if (a.getLevel() == 3) {
				icon = "icon-weibiaoti3";
			} else if (a.getLevel() == 4) {
				icon = "icon-quyu";
			} else if (a.getLevel() == 5) {
				icon = "icon-jiebian2";
			}
			a.setName("<i class='iconfont " + icon + "'></i>&nbsp "
					+ a.getName());
		}
		List<AreaBean> listBeans = BeanUtil.mapList(list, AreaBean.class);
		return listBeans;
	}

	public List<AreaBean> searchAreaByNameOrCode(String nameOrCode) {
		List<AreaBean> listBean = new ArrayList<AreaBean>();
		if (StringUtil.isNotEmpty(nameOrCode)) {
			List<Area> list = areaDao.searchAreaByNameOrCode(StringUtil
					.getEscapeString(nameOrCode));
			listBean = BeanUtil.mapList(list, AreaBean.class);
			return listBean;
		}
		return listBean;
	}

	public Area getAreaByCode(String code) {
		return areaDao.getAreaByCode(code);
	}

	
}
