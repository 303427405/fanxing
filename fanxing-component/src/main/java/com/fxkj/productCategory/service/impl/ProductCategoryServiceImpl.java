package com.fxkj.productCategory.service.impl;

import java.util.HashMap;
import java.util.Map;

import com.fxkj.core.base.BaseService;
import com.fxkj.core.base.MsgUtil;
import com.fxkj.core.base.PageInfo;
import com.fxkj.core.common.Commons;
import com.fxkj.core.common.SecurityUtils;
import com.fxkj.core.utils.DateUtils;
import com.fxkj.core.utils.StringUtil;
import com.fxkj.core.utils.UUIDUtil;
import com.fxkj.enums.EnabledEnum;
import com.fxkj.productCategory.bean.ProductCategorySearch;
import com.fxkj.productCategory.dao.ProductCategoryDao;
import com.fxkj.productCategory.entity.ProductCategory;
import com.fxkj.productCategory.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 商品分类信息
 */
@Service("productCategoryService")
@Transactional
public class ProductCategoryServiceImpl extends BaseService implements
        ProductCategoryService {

	@Autowired
	private ProductCategoryDao productCategoryDao;

    /**
     * 分页显示商品分类信息
     * @param search
     * @param parameterMap
     * @return
     */
	public PageInfo<ProductCategory> findProductCategory(
            ProductCategorySearch search, Map<String, Object> parameterMap) {
		if (StringUtil.isEmpty(search.getOrgCode())) {
			String orgCode = SecurityUtils.getCurrentOperator().getOrgCode();
			if (!orgCode.startsWith(Commons.COUNTRYCODE)) {
				search.setOrgCode(orgCode);
			}
		}
		parameterMap.put("pc", search);
		return productCategoryDao.findProductCategory(parameterMap);
	}


    /**
     * 修改商品分类状态
     * @param id
     * @param flg
     * @return
     */
	public MsgUtil changeEnabled(Integer id, Boolean flg) {
		if (id != null && flg != null) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", id);
			map.put("flg", flg);
			int temp = productCategoryDao.changeEnabled(map);
			return new MsgUtil(temp > 0 ? true : false, temp > 0 ? SUCCESS_MSG
					: ERROR_MSG);
		} else {
			return new MsgUtil(false, PARAM_MSG);
		}
	}

    /**
     * 添加商品分类信息
     * @param productCategory
     * @return
     */
    public MsgUtil addProductCategory(ProductCategory productCategory) {
        if (productCategory != null) {
            productCategory.setCode(UUIDUtil.getUUID());
            productCategory.setStatus(EnabledEnum.ENABLED.getCode());
            int temp = productCategoryDao.addProductCategory(productCategory);
            return new MsgUtil(temp > 0 ? true : false, temp > 0 ? SUCCESS_MSG
                    : ERROR_MSG);
        } else {
            return new MsgUtil(false, PARAM_MSG);
        }
      }

    /**
     * 根据ID获取商品分类
     * @param id
     * @return
     */
    public ProductCategory getProductCategoryById(Integer id) {
        return productCategoryDao.getProductCategoryById(id);
    }


    /**
     * 修改商品分类
     * @param productCategory
     * @return
     */
    public MsgUtil updateProductCategoryById(ProductCategory productCategory) {
        if (productCategory != null) {
            int temp = productCategoryDao.updateProductCategoryById(productCategory);
            return new MsgUtil(temp > 0 ? true : false, temp > 0 ? SUCCESS_MSG
                    : ERROR_MSG);
        } else {
            return new MsgUtil(false, PARAM_MSG);
        }
    }

}
