package com.fxkj.shortcut.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fxkj.core.base.BaseService;
import com.fxkj.core.base.MsgUtil;
import com.fxkj.core.base.PageInfo;
import com.fxkj.core.common.Commons;
import com.fxkj.core.common.SecurityUtils;
import com.fxkj.core.security.MyUserDetailInfo;
import com.fxkj.core.utils.BeanUtil;
import com.fxkj.core.utils.StringUtil;
import com.fxkj.security.bean.PermissionsBean;
import com.fxkj.security.dao.PermissionsDao;
import com.fxkj.security.entity.Permissions;
import com.fxkj.security.service.OperatorService;
import com.fxkj.shortcut.bean.ShortcutBean;
import com.fxkj.shortcut.dao.ShortcutDao;
import com.fxkj.shortcut.entity.Shortcut;
import com.fxkj.shortcut.service.ShortcutService;

@Service("shortcutService")
@Transactional
public class ShortcutServiceImpl extends BaseService implements ShortcutService {

	@Autowired
	private OperatorService operatorService;

	@Autowired
	private ShortcutDao shortcutDao;

	@Autowired
	private PermissionsDao permissionsDao;

	public PageInfo<Shortcut> findShortcut(Map<String, Object> parameterMap) {
		return shortcutDao.findShortcut(parameterMap);
	}

	public List<ShortcutBean> getShortcutByCurrentOperator() {
		MyUserDetailInfo o = SecurityUtils.getCurrentOperator();
		List<ShortcutBean> listBean = new ArrayList<ShortcutBean>();
		if (o != null && o.getId() != null) {
			List<Shortcut> list = shortcutDao.getShortcutByCurrentOperator(o
					.getId());
			listBean = BeanUtil.mapList(list, ShortcutBean.class);
		}
		return listBean;
	}

	public List<PermissionsBean> getOperatorPermissionsForShortcut(
			Integer operatorId) {
		List<Permissions> list = shortcutDao
				.getOperatorPermissionsForShortcut(operatorId);
		for (int i = 0; i < list.size(); i++) {
			Permissions p = list.get(i);
			if (p.getChecked() == true) {
				list.remove(p);
				i--;
			} else {
				p.setChkDisabled(StringUtil.isEmpty(p.getWebUrl()) ? true
						: false);
				p.setName(String.format(Commons.SHORTCUT_TREE_NAME_STYLE,
						p.getIcon())
						+ p.getName());
				p.setIcon(null);
				p.setOpen(true);
			}
		}
		List<PermissionsBean> beansList = BeanUtil.mapList(list,
				PermissionsBean.class);
		for (PermissionsBean p : beansList) {
			for (PermissionsBean pp : beansList) {
				if (p.getId().equals(pp.getParentId())) {
					p.setChkDisabled(true);
					break;
				}
			}
		}
		return beansList;
	}

	public MsgUtil addShortcut(Shortcut s) {
		Integer operatorId = SecurityUtils.getCurrentOperator().getId();
		if (s != null && s.getPermissionsId() != null && operatorId != null) {
			int count = shortcutDao.getShortcutCountByOperatorId(operatorId);
			if (count < Commons.SHORTCUT_COUNT) {
				s.setOperatorId(operatorId);
				int temp = shortcutDao.addShortcut(s);
				return new MsgUtil(temp > 0 ? true : false,
						temp > 0 ? SUCCESS_MSG : ERROR_MSG);
			} else {
				return new MsgUtil(false, String.format(SHORTCUT_COUNT_ERROR_MSG, Commons.SHORTCUT_COUNT));
			}

		} else {
			return new MsgUtil(false, PARAM_MSG);
		}

	}

	public MsgUtil delShortcut(String ids) {
		if (StringUtil.isNotEmpty(ids)) {
			List<Integer> list = StringUtil.parseInteger(ids);
			for (Integer id : list) {
				shortcutDao.deleteShortcut(id);
			}
			return new MsgUtil(true, SUCCESS_MSG);
		} else {
			return new MsgUtil(false, PARAM_MSG);
		}
	}
}
