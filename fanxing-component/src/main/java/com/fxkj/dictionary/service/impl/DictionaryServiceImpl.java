package com.fxkj.dictionary.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fxkj.core.base.BaseService;
import com.fxkj.core.base.MsgUtil;
import com.fxkj.core.base.PageInfo;
import com.fxkj.core.utils.BeanUtil;
import com.fxkj.core.utils.StringUtil;
import com.fxkj.dictionary.bean.DictionaryBean;
import com.fxkj.dictionary.dao.DictionaryDao;
import com.fxkj.dictionary.entity.Dictionary;
import com.fxkj.dictionary.service.DictionaryService;

@Service("dictionaryService")
@Transactional
public class DictionaryServiceImpl extends BaseService implements
		DictionaryService {
	@Autowired
	private DictionaryDao dictionaryDao;

	public PageInfo<Dictionary> findDictionary(Map<String, Object> parameterMap) {
		return dictionaryDao.findDictionary(parameterMap);
	}

	public MsgUtil addDictionary(Dictionary d) {
		int temp = dictionaryDao.addDictionary(d);
		return new MsgUtil(temp > 0 ? true : false, temp > 0 ? SUCCESS_MSG
				: ERROR_MSG);
	}

	public List<DictionaryBean> getDicItemByParentCode(String code) {
		List<Dictionary> list = dictionaryDao.getDicItemByParentCode(code);
		List<DictionaryBean> listBeans = BeanUtil.mapList(list,
				DictionaryBean.class);
		return listBeans;
	}

	public MsgUtil checkCodeRepeatByLeaf(String code, Integer leaf,
			Integer parentId) {
		if (StringUtil.isNotEmpty(code)) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("code", code);
			map.put("leaf", leaf);
			map.put("parentId", parentId);
			List<Long> temp = dictionaryDao.checkCodeRepeatByLeaf(map);
			if (temp != null)
				if (temp.size() == 0)
					return new MsgUtil(true, "");
				else if (temp.size() == 1)
					return new MsgUtil(true, temp.get(0) + "");
			return new MsgUtil(false, ERROR_MSG);
		} else {
			return new MsgUtil(false, PARAM_MSG);
		}
	}

	public MsgUtil changeEnabled(Integer id, Boolean flg) {
		if (id != null && flg != null) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", id);
			map.put("flg", flg);
			int temp = dictionaryDao.changeEnabled(map);
			return new MsgUtil(temp > 0 ? true : false, temp > 0 ? SUCCESS_MSG
					: ERROR_MSG);
		} else {
			return new MsgUtil(false, PARAM_MSG);
		}
	}

}
