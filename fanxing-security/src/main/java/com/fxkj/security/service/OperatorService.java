package com.fxkj.security.service;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.fxkj.core.base.MsgUtil;
import com.fxkj.core.base.PageInfo;
import com.fxkj.security.entity.Operator;

public interface OperatorService {

	/**
	 * 用户列表
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
	public MsgUtil addOperator(Operator o);
	
	/**
	 * 添加用户(含头像)
	 * @param o
	 * @param roleIds
	 * @param request
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @return
	 */
	public MsgUtil addOperator(Operator o, String roleIds, HttpServletRequest request, Integer x, Integer y, Integer w, Integer h)throws IOException ;
	
	public MsgUtil registerNoAuth(Operator o);
	
	/**
	 * 修改用户
	 * @param o
	 * @param roleIds
	 * @param request
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @return
	 */
	public MsgUtil updateOperatorById(Operator o, String roleIds, HttpServletRequest request,
                                      Integer x, Integer y, Integer w, Integer h)throws IOException;
	
	
	/**
	 * 修改个人信息
	 * @param o
	 * @return
	 */
	public MsgUtil updateProfileById(Operator o);
	
	/**
	 * 修改用户密码
	 * @param password
	 * @return
	 */
	public MsgUtil updatePasswordById(String password) ;
	/**
	 * 重置密码
	 * @param o
	 * @return
	 */
	public MsgUtil resetPassword(Operator o);
	/**
	 * 改变用户状态
	 * 
	 * @param id
	 * @param flg
	 * @return
	 */
	public MsgUtil changeEnabled(Integer id, Boolean flg);
	
	/**
	 * 校验用户名是否重复
	 * @param loginName
	 * @return
	 */
	public MsgUtil checkLoginNameRepeat(String loginName);
	
	/**
	 * 修改用户密码时校验原密码
	 * @param password
	 * @return
	 */
	public MsgUtil checkSelfPasswordById(String password);
	
	
}
