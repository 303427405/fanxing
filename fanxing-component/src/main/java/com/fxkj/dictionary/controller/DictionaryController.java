package com.fxkj.dictionary.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fxkj.core.base.BaseAction;
import com.fxkj.core.base.MsgUtil;
import com.fxkj.dictionary.bean.DictionaryBean;
import com.fxkj.dictionary.bean.DictionarySearch;
import com.fxkj.dictionary.service.DictionaryService;
import com.fxkj.enums.EnabledEnum;

@Controller
@RequestMapping("/dictionaryController")
public class DictionaryController extends BaseAction {
	@Autowired
	private DictionaryService dictionaryService;

	@RequestMapping(value = "findDictionary")
	public String findDictionary(DictionarySearch search,
			HttpServletRequest request, ModelMap modelMap) {
		Map<String, Object> parameterMap = getGridParam(request);
		parameterMap.put("d", search);
		modelMap.put("page", dictionaryService.findDictionary(parameterMap));
		modelMap.put("d", search);
		modelMap.put("enabledEnum", EnabledEnum.values());
		return "page/dictionary/list";
	}

	@RequestMapping(value = "changeEnabled", method = RequestMethod.POST)
	@ResponseBody
	public MsgUtil changeEnabled(Integer id, Boolean flg) {
		return dictionaryService.changeEnabled(id, flg);
	}
	
	@RequestMapping(value = "getItemByParentCode", method = RequestMethod.POST)
	@ResponseBody
	public List<DictionaryBean> getItemByParentCode(String code) {
		return dictionaryService.getDicItemByParentCode(code);
	}
	
	@RequestMapping(value = "getItemByParentCodeNoAuth", method = RequestMethod.POST)
	@ResponseBody
	public List<DictionaryBean> getItemByParentCodeNoAuth(String code) {
		return dictionaryService.getDicItemByParentCode(code);
	}

}
