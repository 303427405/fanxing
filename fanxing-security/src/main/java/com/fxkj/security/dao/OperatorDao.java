package com.fxkj.security.dao;

import java.util.List;
import java.util.Map;

import com.fxkj.core.base.PageInfo;
import com.fxkj.security.entity.Operator;

public interface OperatorDao {

	/**
	 * 字典项列表
	 * 
	 * @param parameterMap
	 * @return
	 */
	public PageInfo<Operator> findOperator(Map<String, Object> parameterMap);

	/**
	 * 根据用户名检索实体
	 * 
	 * @param loginName
	 * @return
	 */
	public Operator getOperatorByLoginName(String loginName);
	
	/**
	 * 根据id检索实体
	 * @param id
	 * @return
	 */
	public Operator getOperatorById(Integer id);
	
	
	

	/**
	 * 添加用户
	 * 
	 * @param o
	 * @return
	 */
	public Integer addOperator(Operator o);

	/**
	 * 修改用户
	 * 
	 * @param o
	 * @return
	 */
	public Integer updateOperatorById(Operator o);
	
	/**
	 * 修改密码
	 * @param o
	 * @return
	 */
	public Integer updatePasswordById(Operator o);
	
	
	/**
	 * 锁屏或是解锁
	 * @param parameterMap
	 * @return
	 */
	public Integer lockOrUnlockScreen(Map<String, Object> parameterMap);
	
	
	

	/**
	 * 校验用户名是否重复
	 * 
	 * @param parameterMap
	 * @return
	 */
	public List<Integer> checkLoginNameRepeat(Map<String, Object> parameterMap);
	
	/**
	 * 修改用户密码时校验原密码
	 * @param parameterMap
	 * @return
	 */
	public Integer checkSelfPasswordById(Map<String, Object> parameterMap);
	
	

	/**
	 * 改变用户状态
	 * 
	 * @param map
	 * @return
	 */
	public Integer changeEnabled(Map<String, Object> map);
}
