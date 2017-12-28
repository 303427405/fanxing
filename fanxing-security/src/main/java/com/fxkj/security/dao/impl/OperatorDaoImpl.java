package com.fxkj.security.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.fxkj.core.base.BaseDaoImpl;
import com.fxkj.core.base.PageInfo;
import com.fxkj.core.exception.DaoException;
import com.fxkj.security.dao.OperatorDao;
import com.fxkj.security.entity.Operator;

@Repository("operatorDao")
@SuppressWarnings("unchecked")
public class OperatorDaoImpl extends BaseDaoImpl<Operator> implements
		OperatorDao {

	public PageInfo<Operator> findOperator(Map<String, Object> parameterMap) {
		return getPageByParamMap(new PageInfo<Operator>(),
				"operator.countOperator", "operator.listOperator", parameterMap);
	}

	public Operator getOperatorById(Integer id) {
		return (Operator) getSqlMapClientTemplate().queryForObject(
				"operator.getOperatorById", id);
	}

	public Operator getOperatorByLoginName(String loginName) {
		List<Operator> list = (List<Operator>) getSqlMapClientTemplate()
				.queryForList("operator.getOperatorByLoginName", loginName);
		if (list.size() > 1)
			throw new DaoException("登录用户名有重复!");
		else {
			if (list.size() == 1)
				return list.get(0);
			else
				return null;
		}
	}

	public Integer addOperator(Operator o) {
		return (Integer) getSqlMapClientTemplate().insert(
				"operator.addOperator", o);
	}

	public List<Integer> checkLoginNameRepeat(Map<String, Object> parameterMap) {
		return (List<Integer>) getSqlMapClientTemplate().queryForList(
				"operator.checkLoginNameRepeat", parameterMap);
	}

	public Integer checkSelfPasswordById(Map<String, Object> parameterMap) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"operator.checkSelfPasswordById", parameterMap);
	}

	public Integer changeEnabled(Map<String, Object> map) {
		return (Integer) getSqlMapClientTemplate().update(
				"operator.changeEnabled", map);
	}

	public Integer lockOrUnlockScreen(Map<String, Object> parameterMap) {
		return (Integer) getSqlMapClientTemplate().update(
				"operator.lockOrUnlockScreen", parameterMap);
	}

	public Integer updateOperatorById(Operator o) {
		return (Integer) getSqlMapClientTemplate().update(
				"operator.updateOperatorById", o);
	}

	public Integer updatePasswordById(Operator o) {
		return (Integer) getSqlMapClientTemplate().update(
				"operator.updatePasswordById", o);
	}

}
