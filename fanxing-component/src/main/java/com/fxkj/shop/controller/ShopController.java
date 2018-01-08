package com.fxkj.shop.controller;

import com.fxkj.core.base.BaseAction;
import com.fxkj.core.base.MsgUtil;
import com.fxkj.productCategory.bean.ProductCategorySearch;
import com.fxkj.productCategory.entity.ProductCategory;
import com.fxkj.productCategory.service.ProductCategoryService;
import com.fxkj.shop.bean.ShopSearch;
import com.fxkj.shop.entity.ShopInfo;
import com.fxkj.shop.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/shopController")
public class ShopController extends BaseAction {

	@Autowired
	private ShopService shopService;

	@RequestMapping(value = "findShop")
	public String findProductCategory(ShopSearch search,
			HttpServletRequest request, ModelMap modelMap) {
		Map<String, Object> parameterMap = getGridParam(request);
		modelMap.put("page", shopService.findShopInfo(search,
				parameterMap));
		modelMap.put("pc", search);
		return "page/shop/list";
	}


    @RequestMapping(value = "addShop" , method = RequestMethod.POST)
    @ResponseBody
    public MsgUtil addShop(ShopInfo shopInfo) {
        return shopService.addShopInfo(shopInfo);
    }

    @RequestMapping(value = "add_view")
    public String addView() {
        return "page/shop/add";
    }


    @RequestMapping(value = "changeEnabled" , method = RequestMethod.POST)
    @ResponseBody
    public MsgUtil changeEnabled(Integer id , Boolean flg) {
        return shopService.changeEnabled(id,flg);
    }




}
