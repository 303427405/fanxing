package com.fxkj.core.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.fxkj.core.utils.DateUtils;
import com.fxkj.core.utils.StringUtil;
import com.fxkj.security.dao.OperatorDao;
import com.fxkj.security.dao.RoleDao;
import com.fxkj.security.entity.Operator;
import com.fxkj.security.entity.Role;

public class MyUserDetailService implements UserDetailsService {

	@Autowired
	private OperatorDao operatorDao;

	@Autowired
	private RoleDao roleDao;

	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		Operator o = operatorDao.getOperatorByLoginName(username);
		if (o != null) {
			if(StringUtil.isNotEmpty(o.getValidDate())){
				int day=DateUtils.getBetweenDays(DateUtils.shortDate(new Date()), o.getValidDate());
				if(day<=0){
					throw new BadCredentialsException("对不起，你的账号使用期限已到，请你<a href='../loginController/expiredDateNoAuth.do' style='cursor: pointer'>续费 </a>!");
				}
			}
			
			Collection<GrantedAuthority> grantedAuths = getGrantedAuthorities(o);
			MyUserDetailInfo user = new MyUserDetailInfo(o.getId(),
					o.getLoginName(), o.getPassword(),
					1 == o.getEnabled() ? true : false,
					1 == o.getAccountNonExpired() ? true : false, grantedAuths,
					o.getIsLockScreen(), o.getEmail(), o.getImgPath(),o.getOrgCode());
			// User user = new User(o.getLoginName(), o.getPassword(),
			// 1==o.getEnabled()?true:false,
			// 1==o.getAccountNonExpired()?true:false,
			// 1==o.getAccountNonExpired()?true:false,
			// 1==o.getAccountNonExpired()?true:false,
			// grantedAuths);
			return user;
		} else {
			throw new BadCredentialsException("用户名或密码错误！");
		}

	}

	// 取得用户的权限(角色,可用的)
	private Collection<GrantedAuthority> getGrantedAuthorities(Operator o) {
		Collection<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
		for (Role r : roleDao.getEnabledRoleListByOperatorId(o.getId())) {
			auths.add(new SimpleGrantedAuthority("ROLE_" + r.getId()));
		}
		return auths;
	}

}
