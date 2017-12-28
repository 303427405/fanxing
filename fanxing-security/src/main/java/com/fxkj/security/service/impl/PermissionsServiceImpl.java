package com.fxkj.security.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fxkj.core.base.BaseService;
import com.fxkj.core.common.Commons;
import com.fxkj.core.common.SecurityUtils;
import com.fxkj.core.redis.RedisService;
import com.fxkj.core.utils.BeanUtil;
import com.fxkj.core.utils.StringUtil;
import com.fxkj.security.bean.PermissionsBean;
import com.fxkj.security.dao.OperatorDao;
import com.fxkj.security.dao.PermissionsDao;
import com.fxkj.security.dao.RoleDao;
import com.fxkj.security.entity.Operator;
import com.fxkj.security.entity.Permissions;
import com.fxkj.security.entity.Role;
import com.fxkj.security.service.PermissionsService;

@Service("permissionsService")
@Transactional
public class PermissionsServiceImpl extends BaseService implements
		PermissionsService {
	@Autowired
	private PermissionsDao permissionsDao;
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private RedisService redisService;

	@Autowired
	private OperatorDao operatorDao;

	public Integer addPerssions(Permissions p) {
		return permissionsDao.addPerssions(p);
	}

	public List<PermissionsBean> getPermissionsForAuth(Integer roleId,
			Integer flg,Boolean disabled) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("flg", flg);
		map.put("roleId", roleId);
		List<Permissions> list = permissionsDao
				.getPermissionsEnabledForAuthorize(map);
		for (Permissions p : list) {
			p.setName(String.format(Commons.SHORTCUT_TREE_NAME_STYLE,p.getIcon())+ p.getName());
			p.setIcon(null);
			p.setOpen(true);
			if(Boolean.TRUE==disabled){
				p.setChkDisabled(true);
			}
		}
		List<PermissionsBean> beansList = BeanUtil.mapList(list,
				PermissionsBean.class);
		return beansList;
	}

	public List<PermissionsBean> getPermissionsByCurrentOperator() {
		Operator o = operatorDao.getOperatorByLoginName(SecurityUtils
				.getCurrentOperatorName());
		List<PermissionsBean> beansList = new ArrayList<PermissionsBean>();
		if (o != null) {
			List<Role> roleList = roleDao.getEnabledRoleListByOperatorId(o
					.getId());
			StringBuffer ids = new StringBuffer();
			for (int i = 0; i < roleList.size(); i++) {
				ids.append(roleList.get(i).getId() + ",");
			}
			if (roleList.size() > 0) {
				ids.deleteCharAt(ids.length() - 1);
			}
			List<Permissions> list = new ArrayList<Permissions>();
			if (StringUtil.isNotEmpty(ids.toString()))
				list = permissionsDao
						.getPermissionListByRoleIds(ids.toString());
			beansList = BeanUtil.mapList(list, PermissionsBean.class);
		}
		return beansList;
	}
	
}
