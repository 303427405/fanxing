package com.fxkj.dictionary.service;

import java.util.List;
import java.util.Map;

import com.fxkj.core.base.MsgUtil;
import com.fxkj.core.base.PageInfo;
import com.fxkj.dictionary.bean.DictionaryBean;
import com.fxkj.dictionary.entity.Dictionary;

public interface DictionaryService {
	/**
	 * 字典项列表
	 * 
	 * @param parameterMap
	 * @return
	 */
	public PageInfo<Dictionary> findDictionary(Map<String, Object> parameterMap);
	
	/**
	 * 添加实体
	 * @param d
	 * @return
	 */
	public MsgUtil addDictionary(Dictionary d); 

	



	/**
	 * 根据父模块code加载字典项子元素(正常状态)
	 * 
	 * @param code
	 * @return
	 */
	public List<DictionaryBean> getDicItemByParentCode(String code);
	
	/**
	 * 校验所在层级code是否重复
	 * @param code
	 * @param leaf
	 * @return
	 */
	public MsgUtil checkCodeRepeatByLeaf(String code, Integer leaf, Integer parentId);
	
	/**
	 * 改变字典项状态
	 * @param id
	 * @param flg
	 * @return
	 */
	public MsgUtil changeEnabled(Integer id, Boolean flg);

}
