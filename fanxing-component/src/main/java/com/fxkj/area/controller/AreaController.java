package com.fxkj.area.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fxkj.area.bean.AreaBean;
import com.fxkj.area.bean.AreaSearchBean;
import com.fxkj.area.service.AreaService;
import com.fxkj.core.base.BaseAction;
import com.fxkj.core.base.MethodLog;

@Controller
@RequestMapping("/areaController")
public class AreaController extends BaseAction {

	@Autowired
	private AreaService areaService;

	@RequestMapping(value = "findArea", method = RequestMethod.POST)
	@ResponseBody
	@MethodLog(remark = "查询区域")
	public List<AreaBean> findArea(AreaSearchBean bean) {
		return areaService.findArea(bean);

	}
	
	@RequestMapping(value = "searchAreaByNameOrCode", method = RequestMethod.POST)
	@ResponseBody
	@MethodLog(remark = "根据名称或编码搜索区域【下拉框】")
	public List<AreaBean> searchAreaByNameOrCode(String nameOrCode) {
		return areaService.searchAreaByNameOrCode(nameOrCode);
	}
}
