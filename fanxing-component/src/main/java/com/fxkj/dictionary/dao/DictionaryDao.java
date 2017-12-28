package com.fxkj.dictionary.dao;

import java.util.List;
import java.util.Map;

import com.fxkj.core.base.BaseDao;
import com.fxkj.core.base.PageInfo;
import com.fxkj.dictionary.entity.Dictionary;

public interface DictionaryDao extends BaseDao {
	/**
	 * 字典项列表
	 * 
	 * @param parameterMap
	 * @return
	 */
	public PageInfo<Dictionary> findDictionary(Map<String, Object> parameterMap);

	/**
	 * 添加字典
	 * 
	 * @param d
	 * @return
	 */
	public Integer addDictionary(Dictionary d);

	/**
	 * 根据父模块code加载字典项子元素,(正常状态)
	 * 
	 * @param code
	 * @return
	 */
	public List<Dictionary> getDicItemByParentCode(String code);

	/**
	 * 校验所在层级code是否重复
	 * 
	 * @param parameterMap
	 * @return
	 */
	public List<Long> checkCodeRepeatByLeaf(Map<String, Object> parameterMap);

	/**
	 * 改变字典项状态
	 * 
	 * @param map
	 * @return
	 */
	public Integer changeEnabled(Map<String, Object> map);

}
