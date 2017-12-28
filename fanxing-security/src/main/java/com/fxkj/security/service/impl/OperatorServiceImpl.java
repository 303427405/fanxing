package com.fxkj.security.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fxkj.area.dao.AreaDao;
import com.fxkj.core.base.BaseService;
import com.fxkj.core.base.MsgUtil;
import com.fxkj.core.base.PageInfo;
import com.fxkj.core.common.Commons;
import com.fxkj.core.common.SecurityUtils;
import com.fxkj.core.utils.DateUtils;
import com.fxkj.core.utils.FileUtils;
import com.fxkj.core.utils.MD5;
import com.fxkj.core.utils.StringUtil;
import com.fxkj.enums.EnabledEnum;
import com.fxkj.security.dao.OperatorDao;
import com.fxkj.security.dao.RoleDao;
import com.fxkj.security.entity.Operator;
import com.fxkj.security.entity.RoleAndOperator;
import com.fxkj.security.service.OperatorService;

@Service("operatorService")
@Transactional
public class OperatorServiceImpl extends BaseService implements OperatorService {

	@Autowired
	private OperatorDao operatorDao;

	@Autowired
	private RoleDao roleDao;
	@Autowired
	private AreaDao areaDao;

	public PageInfo<Operator> findOperator(Map<String, Object> parameterMap) {
		return operatorDao.findOperator(parameterMap);
	}

	public Operator getOperatorByLoginName(String loginName) {
		return operatorDao.getOperatorByLoginName(loginName);
	}

    public Operator getOperatorById(Integer id) {
        /**
         * Operator operator = operatorDao.getOperatorById(id);
         String orgCode = operator.getOrgCode();
         Area area = areaDao.getAreaByCode(orgCode.substring(0,
         orgCode.indexOf("_")));
         operator.setAreaCodes("00," + area.getCodePath());
         return operator;
         */

        return null;
    }

    public MsgUtil addOperator(Operator o) {
		if (o != null && o.getLoginName() != null) {
			o.setPassword(MD5.crypt(Commons.PASSWORD_DEFAULT));
			int temp = operatorDao.addOperator(o);
			return new MsgUtil(temp > 0 ? true : false, temp > 0 ? SUCCESS_MSG
					: ERROR_MSG);
		} else {
			return new MsgUtil(false, PARAM_MSG);
		}

	}

	public MsgUtil registerNoAuth(Operator o) {
		if (o != null && o.getLoginName() != null) {
			o.setPassword(MD5.crypt(o.getPassword()));
			o.setEnabled(EnabledEnum.ENABLED.getCode());
			o.setOrgCode(Commons.ANONYMOUSUSER_ORGCODE);
			int temp = operatorDao.addOperator(o);
			return new MsgUtil(temp > 0 ? true : false, temp > 0 ? SUCCESS_MSG
					: ERROR_MSG);
		} else {
			return new MsgUtil(false, PARAM_MSG);
		}
	}

	public MsgUtil addOperator(Operator o, String roleIds,
			HttpServletRequest request, Integer x, Integer y, Integer w,
			Integer h) throws IOException {
		Map<String, String> map = new HashMap<String, String>();
		MsgUtil msgUtil = uploadFile(map, request, x, y, w, h);
		if (msgUtil != null) {
			return msgUtil;
		}
		if (o != null && o.getLoginName() != null) {
			Map<String, Object> mapTemp = new HashMap<String, Object>();
			mapTemp.put("loginName", o.getLoginName());
			List<Integer> tempIds = operatorDao.checkLoginNameRepeat(mapTemp);
			if (tempIds != null && tempIds.size() == 0) {
				o.setPassword(MD5.crypt(Commons.PASSWORD_DEFAULT));
				o.setEnabled(EnabledEnum.ENABLED.getCode());
				if (StringUtil.isEmpty(o.getOrgCode())) {
					o.setOrgCode(SecurityUtils.getCurrentOperator()
							.getOrgCode());
				}
				String fileName = map.get(Commons.IMG_KEY_ORIGINAL);
				if (StringUtil.isNotEmpty(fileName))
					o.setImgPath(File.separator
							+ Commons.OPERATOR_HEAD_IMG_PATH + fileName);
				int temp = operatorDao.addOperator(o);
				List<Integer> list = StringUtil.parseInteger(roleIds);
				for (Integer roleId : list) {
					RoleAndOperator ro = new RoleAndOperator();
					ro.setRoleId(roleId);
					ro.setOperatorId(o.getId());
					roleDao.addRoleAndOperator(ro);
				}
				return new MsgUtil(temp > 0 ? true : false,
						temp > 0 ? SUCCESS_MSG : ERROR_MSG);
			} else {
				return new MsgUtil(false, ERROR_LOGINNAME_REPEAT_MSG);
			}
		} else {
			return new MsgUtil(false, PARAM_MSG);
		}
	}

	public MsgUtil updateProfileById(Operator o) {
		if (o.getId() != null) {
			int temp = operatorDao.updateOperatorById(o);
			return new MsgUtil(temp > 0 ? true : false, temp > 0 ? SUCCESS_MSG
					: ERROR_MSG);
		} else {
			return new MsgUtil(false, PARAM_MSG);
		}
	}

	public MsgUtil updateOperatorById(Operator o, String roleIds,
			HttpServletRequest request, Integer x, Integer y, Integer w,
			Integer h) throws IOException {
		if (o.getId() != null) {
			Map<String, String> map = new HashMap<String, String>();
			MsgUtil msgUtil = uploadFile(map, request, x, y, w, h);
			if (msgUtil != null) {
				return msgUtil;
			}
			String fileName = map.get(Commons.IMG_KEY_ORIGINAL);
			if (StringUtil.isNotEmpty(fileName))
				o.setImgPath(File.separator + Commons.OPERATOR_HEAD_IMG_PATH
						+ fileName);
			int temp = operatorDao.updateOperatorById(o);
			List<Integer> list = StringUtil.parseInteger(roleIds);
			if (list.size() > 0) {
				roleDao.deleteRoleByOperatorId(o.getId());
				for (Integer roleId : list) {
					RoleAndOperator ro = new RoleAndOperator();
					ro.setRoleId(roleId);
					ro.setOperatorId(o.getId());
					roleDao.addRoleAndOperator(ro);
				}
			}
			return new MsgUtil(temp > 0 ? true : false, temp > 0 ? SUCCESS_MSG
					: ERROR_MSG);
		} else {
			return new MsgUtil(false, PARAM_MSG);
		}
	}

	public MsgUtil updatePasswordById(String password) {
		if (StringUtil.isNotEmpty(password)) {
			Operator o = getOperatorByLoginName(SecurityUtils
					.getCurrentOperatorName());
			if (o != null) {
				Operator op = new Operator();
				op.setId(o.getId());
				op.setPassword(MD5.crypt(password));
				int temp = operatorDao.updatePasswordById(op);
				return new MsgUtil(temp > 0 ? true : false,
						temp > 0 ? SUCCESS_MSG : ERROR_MSG);
			}
			return new MsgUtil(false, ERROR_MSG);
		} else {
			return new MsgUtil(false, PARAM_MSG);
		}
	}

	public MsgUtil resetPassword(Operator o) {
		if (o.getId() != null) {
			o.setPassword(MD5.crypt(Commons.PASSWORD_DEFAULT));
			int temp = operatorDao.updateOperatorById(o);
			return new MsgUtil(temp > 0 ? true : false, temp > 0 ? (SUCCESS_MSG
					+ "【新密码：" + Commons.PASSWORD_DEFAULT + "】") : ERROR_MSG);
		} else {
			return new MsgUtil(false, PARAM_MSG);
		}
	}

	public MsgUtil changeEnabled(Integer id, Boolean flg) {
		if (id != null && flg != null) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", id);
			map.put("flg", flg);
			int temp = operatorDao.changeEnabled(map);
			return new MsgUtil(temp > 0 ? true : false, temp > 0 ? SUCCESS_MSG
					: ERROR_MSG);
		} else {
			return new MsgUtil(false, PARAM_MSG);
		}
	}

	public MsgUtil checkSelfPasswordById(String password) {
		if (StringUtil.isNotEmpty(password)) {
			Operator o = getOperatorByLoginName(SecurityUtils
					.getCurrentOperatorName());
			if (o != null) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", o.getId());
				map.put("password", MD5.crypt(password));
				int temp = operatorDao.checkSelfPasswordById(map);
				return new MsgUtil(temp > 0 ? true : false,
						temp > 0 ? SUCCESS_MSG : ERROR_MSG);
			}
			return new MsgUtil(false, ERROR_MSG);
		} else {
			return new MsgUtil(false, PARAM_MSG);
		}
	}

	public MsgUtil checkLoginNameRepeat(String loginName) {
		if (StringUtil.isNotEmpty(loginName)) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("loginName", loginName);
			List<Integer> temp = operatorDao.checkLoginNameRepeat(map);
			if (temp != null && temp.size() == 0)
				return new MsgUtil(true, SUCCESS_MSG);
			return new MsgUtil(false, ERROR_MSG);
		} else {
			return new MsgUtil(false, PARAM_MSG);
		}
	}

	/**
	 * 上传头像
	 * 
	 * @param map
	 * @param request
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @return
	 * @throws IOException
	 */
	private MsgUtil uploadFile(Map<String, String> map,
			HttpServletRequest request, Integer x, Integer y, Integer w,
			Integer h) throws IOException {
		String savePath = UPLOAD_ROOT_PATH + Commons.OPERATOR_HEAD_IMG_PATH;
		String newName = DateUtils.longDate();
		MsgUtil msgUtil = FileUtils.uploadPhoto(request, map, savePath,
				newName, true, false, x, y, w, h,
				Commons.OPERATOR_HEAD_IMG_size);
		if (msgUtil != null) {
			return msgUtil;
		}
		return null;
	}
}
