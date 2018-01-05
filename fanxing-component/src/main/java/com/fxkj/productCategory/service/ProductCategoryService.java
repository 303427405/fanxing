package com.fxkj.productCategory.service;

import com.fxkj.core.base.MsgUtil;
import com.fxkj.core.base.PageInfo;
import com.fxkj.productCategory.bean.ProductCategorySearch;
import com.fxkj.productCategory.entity.ProductCategory;

import java.util.Map;

/**
 * 商品分类信息
 */
public interface ProductCategoryService {

    /**
     * 分页显示商品信息
     * @param search
     * @param parameterMap
     * @return
     */
    PageInfo<ProductCategory> findProductCategory(
            ProductCategorySearch search, Map<String, Object> parameterMap);


    /**
     * 修改商品信息分类状态
     * @param id
     * @param flg
     * @return
     */
    MsgUtil changeEnabled(Integer id, Boolean flg);


    /**
     * 添加商品分类信息
     * @param productCategory
     * @return
     */
    MsgUtil addProductCategory(ProductCategory productCategory);


    /**
     * 根据ID获取商品分类
     * @param id
     * @return
     */
    ProductCategory getProductCategoryById(Integer id);


    /**
     * 修改商品分类
     * @param productCategory
     * @return
     */
    MsgUtil updateProductCategoryById(ProductCategory productCategory);

}
