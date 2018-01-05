package com.fxkj.productCategory.dao.impl;

import java.util.Map;

import com.fxkj.core.base.BaseDaoImpl;
import com.fxkj.core.base.MsgUtil;
import com.fxkj.core.base.PageInfo;
import com.fxkj.productCategory.dao.ProductCategoryDao;
import com.fxkj.productCategory.entity.ProductCategory;
import org.springframework.stereotype.Repository;


/**
 * 商品分类
 */
@Repository("productCategoryDao")
public class ProductCategoryDaoImpl extends BaseDaoImpl<ProductCategory>
		implements ProductCategoryDao {

    /**
     * 分页显示商品分类信息
     * @param parameterMap
     * @return
     */
	public PageInfo<ProductCategory> findProductCategory(
			Map<String, Object> parameterMap) {
		return getPageByParamMap(new PageInfo<ProductCategory>(),
				"productCategory.countProductCategory",
				"productCategory.listProductCategory", parameterMap);
	}


    /**
     * 修改商品分类状态
     * @param map
     * @return
     */
	public Integer changeEnabled(Map<String, Object> map){
		return (Integer) getSqlMapClientTemplate().update(
				"productCategory.changeEnabled", map);
	}


    /**
     * 添加商品分类
     * @param productCategory
     * @return
     */
    public Integer addProductCategory(ProductCategory productCategory) {
        return (Integer) getSqlMapClientTemplate().insert("productCategory.addProductCategory" , productCategory);
    }

    /**
     * 根据ID获取商品分类ID
     * @param id
     * @return
     */
    public ProductCategory getProductCategoryById(Integer id) {
        return (ProductCategory) getSqlMapClientTemplate().queryForObject("productCategory.getProductCategoryById" , id);
    }

    /**
     * 修改商品分类
     * @param productCategory
     * @return
     */
    public Integer updateProductCategoryById(ProductCategory productCategory) {
        return (Integer) getSqlMapClientTemplate().update("productCategory.updateProductCategoryById",productCategory);
    }
}
