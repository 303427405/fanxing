package com.fxkj.productCategory.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.fxkj.core.base.BaseAction;
import com.fxkj.core.base.MsgUtil;
import com.fxkj.market.entity.MarketInfo;
import com.fxkj.productCategory.bean.ProductCategorySearch;
import com.fxkj.productCategory.entity.ProductCategory;
import com.fxkj.productCategory.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/productCategory")
public class ProductCategoryController extends BaseAction {

	@Autowired
	private ProductCategoryService productCategoryService;

	@RequestMapping(value = "findProductCategory")
	public String findProductCategory(ProductCategorySearch search,
			HttpServletRequest request, ModelMap modelMap) {
		Map<String, Object> parameterMap = getGridParam(request);
		modelMap.put("page", productCategoryService.findProductCategory(search,
				parameterMap));
		modelMap.put("pc", search);
		return "page/productCategory/list";
	}
	
	@RequestMapping(value = "changeEnabled", method = RequestMethod.POST)
	@ResponseBody
	public MsgUtil changeEnabled(Integer id, Boolean flg) {
		return productCategoryService.changeEnabled(id, flg);
	}



    @RequestMapping(value = "add_view", method = RequestMethod.POST)
    public String add_view() {
        return "page/productCategory/add";
    }


    @RequestMapping(value = "addProductCategory", method = RequestMethod.POST)
    @ResponseBody
    public MsgUtil addProductCategory(ProductCategory productCategory) {
        return productCategoryService.addProductCategory(productCategory);
    }


    @RequestMapping(value = "view", method = RequestMethod.POST)
    public String view(Integer id, ModelMap modelMap) {
        modelMap.put("p",productCategoryService.getProductCategoryById(id));
        return "page/productCategory/view";

    }

    @RequestMapping(value = "edit_view", method = RequestMethod.POST)
    public String editView(Integer id, ModelMap modelMap) {
        modelMap.put("p",productCategoryService.getProductCategoryById(id));
        return "page/productCategory/edit";
    }


    @RequestMapping(value = "editProductCategory", method = RequestMethod.POST)
    @ResponseBody
    public MsgUtil editProductCategory(ProductCategory productCategory) {
        return productCategoryService.updateProductCategoryById(productCategory);
    }


}
