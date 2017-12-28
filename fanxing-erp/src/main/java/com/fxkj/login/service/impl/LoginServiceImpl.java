package com.fxkj.login.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fxkj.core.base.BaseService;
import com.fxkj.core.common.SecurityUtils;
import com.fxkj.core.filter.LockScreenUserBean;
import com.fxkj.core.security.MyUserDetailInfo;
import com.fxkj.core.utils.MD5;
import com.fxkj.core.utils.StringUtil;
import com.fxkj.login.service.LoginService;
import com.fxkj.organization.dao.OrganizationDao;
import com.fxkj.security.bean.OperatorSearch;
import com.fxkj.security.dao.OperatorDao;

@Service("loginService")
@Transactional
public class LoginServiceImpl extends BaseService implements LoginService {

	@Autowired(required=true)
	private SessionRegistry sessionRegistry;

	@Autowired
	private OrganizationDao organizationDao;

    @Autowired
    private OperatorDao operatorDao;

	@Override
	public String lockScreen(HttpServletRequest request) {
		MyUserDetailInfo user = SecurityUtils.getCurrentOperator();
		if (user != null && user.getId() != null) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", user.getId());
			map.put("isLockScreen", 1);
			Integer flg = operatorDao.lockOrUnlockScreen(map);
			if (flg == 1) {
				setSessionForLockScreen(request, user.getId(), 1);
				return null;
			}
			return LOCKSCREEN_ERROR_MSG;
		} else {
			return PARAM_MSG;
		}
	}

	public String unLockScreen(HttpServletRequest request, String password) {
		MyUserDetailInfo user = SecurityUtils.getCurrentOperator();
		if (StringUtil.isNotEmpty(password) && user != null
				&& user.getPassword() != null && user.getId() != null) {
			String passwordMD5 = MD5.crypt(password);
			if (passwordMD5.equals(user.getPassword())) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", user.getId());
				map.put("isLockScreen", 0);
				operatorDao.lockOrUnlockScreen(map);
				setSessionForLockScreen(request, user.getId(), 0);
				return null;
			}
			return UNLOCKSCREEN_ERROR_MSG;
		} else {
			return PARAM_MSG;
		}
	}

	/**
	 * 为锁屏用户设置session
	 * 
	 * @param request
	 * @param operatorId
	 * @param flg
	 */
	private void setSessionForLockScreen(HttpServletRequest request,
			Integer operatorId, Integer flg) {
		HttpSession session = request.getSession();
		LockScreenUserBean lock = new LockScreenUserBean();
		lock.setId(operatorId);
		lock.setIsLockScreen(flg);
		session.setAttribute("lockScreenUser", lock);
	}

	@Override
	public List<MyUserDetailInfo> findOnLineUser(OperatorSearch search) {
		List<MyUserDetailInfo> list = SecurityUtils.getOnLineOperator(
				sessionRegistry, search.getLoginName(),search.getOrgCode());
		for (MyUserDetailInfo m:list) {
			m.setOrgName(organizationDao.getOrganizationByCode(m.getOrgCode()).getName());
			
		}
		return list;
	}

}
