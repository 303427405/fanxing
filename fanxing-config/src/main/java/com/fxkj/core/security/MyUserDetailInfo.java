package com.fxkj.core.security;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fxkj.core.utils.StringUtil;

public class MyUserDetailInfo implements UserDetails {

	private static final long serialVersionUID = -3450064362986273896L;

	private final Integer id;
	private final String username;
	private final String password;
	private final boolean enabled; // 是否启用
	private final boolean accountNonExpired;// 判断帐号是否已经过期
	private final Collection<GrantedAuthority> authorities;// 用户角色集合
	private Integer isLockScreen;// 锁屏状态 1： 锁定 0：未锁定
	private String email;
	private String imgPath;
	private String orgCode;
	private String ip;
	
	private Date lastLoginDate;
	private String orgName;

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public Integer getIsLockScreen() {
		return isLockScreen;
	}

	public void setIsLockScreen(Integer isLockScreen) {
		this.isLockScreen = isLockScreen;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Collection<GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	public String getPassword() {
		return this.password;
	}

	public String getUsername() {
		return this.username;
	}

	public boolean isAccountNonExpired() {
		return this.accountNonExpired;
	}

	public boolean isAccountNonLocked() {
		return this.accountNonExpired;
	}

	public boolean isCredentialsNonExpired() {
		return this.accountNonExpired;
	}

	public boolean isEnabled() {
		return this.enabled;
	}

	public MyUserDetailInfo(Integer id, String userName, String password,
			boolean enabled, boolean accountNonExpired,
			Collection<GrantedAuthority> authorities,Integer isLockScreen, String email,String imgPath,String orgCode) {
		if (StringUtil.isEmpty(userName)||StringUtil.isEmpty(password)) {
			throw new IllegalArgumentException("用户名或是密码不能为空！");
		}

		this.username = userName;
		this.password = password;
		this.enabled = enabled;
		this.authorities = authorities;
		this.accountNonExpired = accountNonExpired;
		this.id = id;
		this.isLockScreen=isLockScreen;
		this.email=email;
		this.imgPath=imgPath;
		this.orgCode=orgCode;
	}

	public Integer getId() {
		return id;
	}

	public boolean equals(Object rhs) {
		if ((!(rhs instanceof MyUserDetailInfo)) || (rhs == null)) {
			return false;
		}

		MyUserDetailInfo user = (MyUserDetailInfo) rhs;

		if (!(this.authorities.equals(user.authorities))) {
			return false;
		}

		return ((getPassword().equals(user.getPassword()))
				&& (getUsername().equals(user.getUsername()))
				&& (isAccountNonExpired() == user.isAccountNonExpired())
				&& (isAccountNonLocked() == user.isAccountNonLocked())
				&& (isCredentialsNonExpired() == user.isCredentialsNonExpired()) && (isEnabled() == user
					.isEnabled()));
	}
	

    public boolean equalsIp(MyUserDetailInfo user) {
        return this.ip.equals(user.ip);
    }
    
	@SuppressWarnings("rawtypes")
	public int hashCode() {
		int code = 9792;
		for (Iterator i$ = getAuthorities().iterator(); i$.hasNext();) {
			GrantedAuthority authority = (GrantedAuthority) i$.next();
			code *= authority.hashCode() % 7;
		}
		if (getPassword() != null) {
			code *= getPassword().hashCode() % 7;
		}
		if (getUsername() != null) {
			code *= getUsername().hashCode() % 7;
		}
		if (getIp() != null) {
			code *= getIp().hashCode() % 7;
		}
		if (isAccountNonExpired()) {
			code *= -2;
		}
		if (isAccountNonLocked()) {
			code *= -3;
		}
		if (isCredentialsNonExpired()) {
			code *= -5;
		}
		if (isEnabled()) {
			code *= -7;
		}
		return code;
	}

}