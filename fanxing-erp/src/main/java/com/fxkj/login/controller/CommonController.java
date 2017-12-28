package com.fxkj.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fxkj.core.base.BaseAction;

@Controller
@RequestMapping("/commonController")
public class CommonController extends BaseAction {

	@RequestMapping(value = "/timeout", method = RequestMethod.GET)
	public String timeout() {
		return "page/common/timeout";
	}

}
