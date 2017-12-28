package com.fxkj.dictionary.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.fxkj.core.base.BaseDaoImpl;
import com.fxkj.core.base.PageInfo;
import com.fxkj.dictionary.dao.DictionaryDao;
import com.fxkj.dictionary.entity.Dictionary;

@Repository("dictionaryDao")
@SuppressWarnings("unchecked")
public class DictionaryDaoImpl extends BaseDaoImpl<Dictionary> implements
		DictionaryDao {
	
	
    public PageInfo<Dictionary> findDictionary(Map<String, Object> parameterMap) {
		return getPageByParamMap(new PageInfo<Dictionary>(),
				"dictionary.countDic", "dictionary.listDic", parameterMap);
	}

	public Integer addDictionary(Dictionary d) {
		return (Integer) getSqlMapClientTemplate().insert(
				"dictionary.addDictionary", d);

	}

	public List<Long> checkCodeRepeatByLeaf(Map<String, Object> parameterMap) {
		return (List<Long>) getSqlMapClientTemplate().queryForList(
				"dictionary.checkCodeRepeatByLeaf", parameterMap);
	}

	public List<Dictionary> getDicItemByParentCode(String code) {
		return (List<Dictionary>) getSqlMapClientTemplate().queryForList(
				"dictionary.getDicItemByParentCode", code);
	}

	public Integer changeEnabled(Map<String, Object> map) {
		return (Integer) getSqlMapClientTemplate().update(
				"dictionary.changeEnabled", map);
	}

}
