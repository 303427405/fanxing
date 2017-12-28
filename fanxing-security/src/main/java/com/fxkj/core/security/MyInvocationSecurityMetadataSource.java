package com.fxkj.core.security;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import com.fxkj.core.common.ApplicationContextInit;
import com.fxkj.core.common.Commons;
import com.fxkj.core.redis.RedisService;
import com.fxkj.core.redis.SerializeUtil;
import com.fxkj.security.dao.PermissionsDao;
import com.fxkj.security.dao.RoleDao;

/**
 * 
 * 此类在初始化时，应该取到所有资源及其对应角色的定义
 * 
 * 
 * 
 */
public class MyInvocationSecurityMetadataSource implements
		FilterInvocationSecurityMetadataSource {
	private UrlMatcher urlMatcher = new AntUrlPathMatcher();
	private RedisService redisService;
	
	private PermissionsDao permissionsDao;
	private RoleDao roleDao;

	public MyInvocationSecurityMetadataSource() {
		this.redisService = (RedisService) ApplicationContextInit
				.getApplicationContext().getBean("redisService");

	}

	public MyInvocationSecurityMetadataSource(
			PermissionsDao permissionsDao, RoleDao roleDao,
			RedisService redisService) {
		this.permissionsDao = permissionsDao;
		this.redisService = redisService;
		this.roleDao = roleDao;
		redisService.del(Commons.URLBYTE);
		redisService.del(Commons.BTNBYTE);
		loadResourceDefine();

	}
	@SuppressWarnings("unchecked")
	private Map<String, Collection<ConfigAttribute>> loadResourceDefine() {
		byte[] urlByte = redisService.get(Commons.URLBYTE);
		byte[] btnByte = redisService.get(Commons.BTNBYTE);
		Map<String, Collection<ConfigAttribute>> resourceUrlMap = (Map<String, Collection<ConfigAttribute>>) SerializeUtil
				.unserialize(urlByte);
		Map<String, Collection<ConfigAttribute>> resourceBtnMap = (Map<String, Collection<ConfigAttribute>>) SerializeUtil
				.unserialize(btnByte);
		if (resourceUrlMap == null || resourceBtnMap == null) {
			AuthorizeUtils.clearRedisFoAuthorize(redisService, permissionsDao, roleDao);
			return (Map<String, Collection<ConfigAttribute>>) SerializeUtil
					.unserialize(redisService.get(Commons.URLBYTE));
		}
		return resourceUrlMap;
	}

	/**
	 * 如果返回null则不会调用 决策管理器
	 */
	public Collection<ConfigAttribute> getAttributes(Object object)
			throws IllegalArgumentException {
		String url = ((FilterInvocation) object).getRequestUrl();
		Map<String, Collection<ConfigAttribute>> resourceMap = loadResourceDefine();
		if(resourceMap!=null){
			Iterator<String> ite = resourceMap.keySet().iterator();
			while (ite.hasNext()) {
				String resURL = ite.next();
				if (urlMatcher.pathMatchesUrl(resURL, url)) {
					return resourceMap.get(resURL);
				}
			}
		}
		return null;
	}

	/**
	 * 按钮权限
	 */
	@SuppressWarnings("unchecked")
	public Collection<ConfigAttribute> getButtonPower(String buttonUrl)
			throws IllegalArgumentException {
		byte[] btnByte = redisService.get(Commons.BTNBYTE);
		Map<String, Collection<ConfigAttribute>> resourceBtnMap = (Map<String, Collection<ConfigAttribute>>) SerializeUtil
				.unserialize(btnByte);
		if (resourceBtnMap == null)
			loadResourceDefine();
		Iterator<String> ite = resourceBtnMap.keySet().iterator();
		while (ite.hasNext()) {
			String url = ite.next();
			if (urlMatcher.pathMatchesUrl(url, buttonUrl)) {
				return resourceBtnMap.get(buttonUrl);
			}
		}
		return null;
	}

	public boolean supports(Class<?> clazz) {
		return true;
	}

	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

}
