package com.fxkj.productCategory.dao;

import com.fxkj.core.base.MsgUtil;
import com.fxkj.core.base.PageInfo;
import com.fxkj.productCategory.entity.ProductCategory;

import java.util.Map;


/**
 * 商品分类
 */
public interface ProductCategoryDao {


    /**
     * 分页查询商品分类
     * @param parameterMap
     * @return
     */
    PageInfo<ProductCategory> findProductCategory(
            Map<String, Object> parameterMap);


    /**
     * 启用状态设置
     * @param map
     * @return
     */
    Integer changeEnabled(Map<String, Object> map);


    /**
     * 添加商品分类
     * @param productCategory
     * @return
     */
    Integer addProductCategory(ProductCategory productCategory);


    /**
     * 根据ID获取商户分类ID
     * @param id
     * @return
     */
    ProductCategory getProductCategoryById(Integer id);


    /**
     * 修改商品分类
     * @param productCategory
     * @return
     */
    Integer updateProductCategoryById(ProductCategory productCategory);



}
