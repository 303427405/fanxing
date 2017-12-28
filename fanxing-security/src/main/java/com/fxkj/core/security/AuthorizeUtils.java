package com.fxkj.core.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;

import com.fxkj.core.common.Commons;
import com.fxkj.core.redis.RedisService;
import com.fxkj.core.redis.SerializeUtil;
import com.fxkj.core.utils.StringUtil;
import com.fxkj.enums.EnabledEnum;
import com.fxkj.security.dao.PermissionsDao;
import com.fxkj.security.dao.RoleDao;
import com.fxkj.security.entity.Permissions;
import com.fxkj.security.entity.Role;

public final class AuthorizeUtils {
	private final static Logger logger = LoggerFactory
			.getLogger(AuthorizeUtils.class);

	/**
	 * 重新加载角色和它对应的权限到redis中
	 * 
	 * @param redisService
	 * @param permissionsDao
	 * @param roleDao
	 * @return
	 */
	public static boolean clearRedisFoAuthorize(RedisService redisService,
			PermissionsDao permissionsDao, RoleDao roleDao) {
		try {
			redisService.del(Commons.URLBYTE);
			redisService.del(Commons.BTNBYTE);
			Map<String, Collection<ConfigAttribute>> resourceUrlMap = new HashMap<String, Collection<ConfigAttribute>>();
			Map<String, Collection<ConfigAttribute>> resourceBtnMap = new HashMap<String, Collection<ConfigAttribute>>();
			List<Permissions> list = permissionsDao
					.getPermissionsForRedisAuthorizeByEnabled(EnabledEnum.ENABLED
							.getCode());
			for (Permissions p : list) {
				List<Role> roleList = roleDao
						.getRoleListForRedisAuthorizeByPId(p.getId());
				Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
				for (Role r : roleList) {
					ConfigAttribute ca = new SecurityConfig("ROLE_" + r.getId());
					atts.add(ca);
				}
				if (StringUtil.isNotEmpty(p.getWebUrl()))
					resourceUrlMap.put(p.getWebUrl(), atts);
				if (StringUtil.isNotEmpty(p.getBtnFlg()))
					resourceBtnMap.put(p.getBtnFlg(), atts);
			}
			byte[] mapUrlByte = SerializeUtil.serialize(resourceUrlMap);
			redisService.set(Commons.URLBYTE, mapUrlByte, 7200);
			byte[] mapBtnByte = SerializeUtil.serialize(resourceBtnMap);
			redisService.set(Commons.BTNBYTE, mapBtnByte, 7200);
			logger.info("redis权限数据清理成功");
			return true;
		} catch (Exception e) {
			logger.error("redis权限数据清理失败:" + e.getMessage());
			return false;
		}
	}
}
