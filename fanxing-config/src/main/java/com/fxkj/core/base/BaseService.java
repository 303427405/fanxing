package com.fxkj.core.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

public class BaseService {
	
	public final Logger logger = LoggerFactory.getLogger(getClass());
	
	
	@Value("${common_wechatId}")
	protected      String wechatId;
	
	@Value("${upload_root_path}")
	protected  String UPLOAD_ROOT_PATH;

	protected final String ERROR_MSG = "操作失败！";
	protected final String SUCCESS_MSG = "操作成功！";
	protected final String PARAM_MSG = "请求参数不能为空！";
	
	protected final String SHORTCUT_COUNT_ERROR_MSG ="创建快捷方式的总数不能超过%s个";
	
	protected final String LOCKSCREEN_ERROR_MSG = "锁屏失败！";
	protected final String UNLOCKSCREEN_ERROR_MSG = "解锁失败，密码不正确！";
	
	protected final String ERROR_LOGINNAME_REPEAT_MSG = "用户名已经存在，保存失败！";
	
	
	
	protected final String AUTH_HISTORY_CLEAR_ERROR_MSG = "角色对所对应的历史权限清理失败！";
	protected final String AUTH_MEMORY_CLEAR_ERROR_MSG = "授权时，角色所对应的权限內存清理失败！";
	
	
	protected final String ERROR_CLEAR_REDIS= "内存权限清理失败！";

	protected final String ADDMODE = "add";
	protected final String EDITMODE = "edit";
	protected final String DELMODE = "delete";
	protected final String UPDATEMODE = "update";

}
