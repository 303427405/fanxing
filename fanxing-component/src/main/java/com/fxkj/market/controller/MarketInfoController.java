package com.fxkj.market.controller;

import com.fxkj.core.base.BaseAction;
import com.fxkj.core.base.MsgUtil;
import com.fxkj.dictionary.bean.DictionaryBean;
import com.fxkj.enums.EnabledEnum;
import com.fxkj.market.bean.MarketInfoSearcher;
import com.fxkj.market.bean.MarketInfoVO;
import com.fxkj.market.entity.MarketInfo;
import com.fxkj.market.service.MarketInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 市场信息controller
 * Created By zhangyufei on 2018/1/2
 */
@Controller
@RequestMapping("/marketInfoController")
public class MarketInfoController extends BaseAction{

    @Autowired
    private MarketInfoService marketInfoService;

    @RequestMapping(value = "findMarketInfo")
    public String findDictionary(MarketInfoSearcher search,
                                 HttpServletRequest request, ModelMap modelMap) {
        Map<String, Object> parameterMap = getGridParam(request);
        parameterMap.put("d", search);
        modelMap.put("page", marketInfoService.findMarketInfo(parameterMap));
        modelMap.put("d", search);
        modelMap.put("enabledEnum", EnabledEnum.values());
        return "page/marketInfo/list";
    }


    @RequestMapping(value = "view", method = RequestMethod.POST)
    public String view(Integer id, ModelMap modelMap) {
        modelMap.put("o", marketInfoService.getMarketInfoById(id));
        return "page/marketInfo/view";
    }


    @RequestMapping(value = "add_view", method = RequestMethod.POST)
    public String add_view() {
        return "page/marketInfo/add";
    }


    @RequestMapping(value = "addMarketInfo", method = RequestMethod.POST)
    @ResponseBody
    public MsgUtil addOrganization(MarketInfo marketInfo) {
        return marketInfoService.addMarketInfo(marketInfo);
    }


    @RequestMapping(value = "changeEnabled", method = RequestMethod.POST)
    @ResponseBody
    public MsgUtil changeEnabled(Integer id, Boolean flg) {
        return marketInfoService.changeEnabled(id, flg);
    }


    @RequestMapping(value = "edit_view", method = RequestMethod.POST)
    public String edit_view(Integer id, ModelMap modelMap) {
        modelMap.put("o", marketInfoService.getMarketInfoById(id));
        return "page/marketInfo/edit";
    }


    @RequestMapping(value = "updateOrganizationById", method = RequestMethod.POST)
    @ResponseBody
    public MsgUtil updateOrganizationById(MarketInfo marketInfo) {
        return marketInfoService.updateMarketInfoById(marketInfo);
    }

    @RequestMapping(value = "findMarketInfoByCode", method = RequestMethod.POST)
    @ResponseBody
    public List<MarketInfoVO> findMarketInfoByCode(String areaCode) {
        return marketInfoService.findMarketInfoByCode(areaCode);
    }


}
