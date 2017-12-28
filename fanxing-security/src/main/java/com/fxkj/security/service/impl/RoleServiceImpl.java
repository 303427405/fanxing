package com.fxkj.security.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fxkj.core.base.BaseService;
import com.fxkj.core.base.MsgUtil;
import com.fxkj.core.base.PageInfo;
import com.fxkj.core.redis.RedisService;
import com.fxkj.core.security.AuthorizeUtils;
import com.fxkj.core.utils.BeanUtil;
import com.fxkj.core.utils.StringUtil;
import com.fxkj.enums.EnabledEnum;
import com.fxkj.security.bean.RoleBean;
import com.fxkj.security.dao.PermissionsDao;
import com.fxkj.security.dao.RoleDao;
import com.fxkj.security.entity.Permissions;
import com.fxkj.security.entity.Role;
import com.fxkj.security.entity.RoleAndPermissions;
import com.fxkj.security.service.RoleService;

@Service("roleService")
@Transactional
public class RoleServiceImpl extends BaseService implements RoleService {

	@Autowired
	private RoleDao roleDao;
	@Autowired
	private PermissionsDao permissionsDao;
	@Autowired
	private RedisService redisService;

	public PageInfo<Role> findRole(Map<String, Object> parameterMap) {
		return roleDao.findRole(parameterMap);
	}

	public Role getRoleById(Integer id) {
		return roleDao.getRoleById(id);
	}

	public Role edit_view(Integer id) {
		Role role = new Role();
		if (id != null) {
			role = roleDao.getRoleById(id);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("flg", EnabledEnum.ENABLED.getCode());
			map.put("roleId", id);
			List<Permissions> list = permissionsDao
					.getPermissionsEnabledForAuthorize(map);
			StringBuffer buffer = new StringBuffer();
			for (Permissions p : list) {
				if(p.getChecked()){
					buffer.append(p.getId() + ",");
				}
			}
			if (list.size() > 0) {
				buffer.deleteCharAt(buffer.length() - 1);
			}
			role.setPermissionIds(buffer.toString());
		}

		return role;
	}

	public MsgUtil addRole(Role r) {
		if (r != null) {
			List<Integer> list = StringUtil.parseInteger(r.getPermissionIds());
			if (list.size() == 0) {
				return new MsgUtil(false, PARAM_MSG + "(请为角色授权)");
			}
			r.setEnabled(EnabledEnum.ENABLED.getCode());
			int temp = roleDao.addRole(r);
			for (Integer id : list) {
				RoleAndPermissions rp = new RoleAndPermissions(r.getId(), id);
				roleDao.addRoleAndPermissions(rp);
			}
			AuthorizeUtils.clearRedisFoAuthorize(redisService, permissionsDao,
					roleDao);
			return new MsgUtil(temp > 0 ? true : false, temp > 0 ? SUCCESS_MSG
					: ERROR_MSG);
		} else {
			return new MsgUtil(false, PARAM_MSG);
		}
	}

	public MsgUtil updateRoleById(Role r) {
		if (r.getId() != null) {
			List<Integer> list = StringUtil.parseInteger(r.getPermissionIds());
			if (list.size() == 0) {
				return new MsgUtil(false, PARAM_MSG + "(请为角色授权)");
			}
			roleDao.deletePermissionsListByRoleId(r.getId());
			for (Integer id : list) {
				RoleAndPermissions rp = new RoleAndPermissions(r.getId(), id);
				roleDao.addRoleAndPermissions(rp);
			}
			AuthorizeUtils.clearRedisFoAuthorize(redisService, permissionsDao,
					roleDao);
			int temp = roleDao.updateRoleById(r);
			return new MsgUtil(temp > 0 ? true : false, temp > 0 ? SUCCESS_MSG
					: ERROR_MSG);
		} else {
			return new MsgUtil(false, PARAM_MSG);
		}
	}

	public MsgUtil changeEnabled(Integer id, Boolean flg) {
		if (id != null && flg != null) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", id);
			map.put("flg", flg);
			int temp = roleDao.changeEnabled(map);
			if (temp > 0) {
				boolean status = AuthorizeUtils.clearRedisFoAuthorize(redisService, permissionsDao, roleDao);
				if (!status)
					return new MsgUtil(false, ERROR_CLEAR_REDIS);
				return new MsgUtil(true, SUCCESS_MSG);
			} else {
				return new MsgUtil(false, ERROR_MSG);
			}

		} else {
			return new MsgUtil(false, PARAM_MSG);
		}
	}

	public List<RoleBean> getRoleListForAuthorizeByEnabledAndOperatorId(
			Integer operatorId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("operatorId", operatorId);
		map.put("flg", EnabledEnum.ENABLED.getCode());
		List<RoleBean> list = BeanUtil.mapList(
				roleDao.getRoleListForAuthorizeByEnabledAndOperatorId(map),
				RoleBean.class);
		return list;
	}

	public List<Role> getEnabledRoleListByOperatorId(Integer operatorId) {
		return roleDao.getEnabledRoleListByOperatorId(operatorId);
	}
	
	

}
