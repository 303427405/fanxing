package com.fxkj.shop.dao.impl;

import com.fxkj.core.base.BaseDaoImpl;
import com.fxkj.core.base.PageInfo;
import com.fxkj.productCategory.dao.ProductCategoryDao;
import com.fxkj.productCategory.entity.ProductCategory;
import com.fxkj.shop.dao.ShopDao;
import com.fxkj.shop.entity.ShopInfo;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * 商户信息
 * Created By zhangyufei on 2018/1/3
 */
@Repository("shopDao")
public class ShopDaoImpl extends BaseDaoImpl<ShopInfo>
        implements ShopDao {

    /**
     *  分页获取商户信息
     * @param parameterMap
     * @return
     */
    public PageInfo<ShopInfo> findShopInfo(Map<String, Object> parameterMap) {

        return getPageByParamMap(new PageInfo<ShopInfo>(),
                "shopInfo.countShopInfo",
                "shopInfo.listShopList", parameterMap);
    }

    /**
     * 添加商户
     * @param shopInfo
     * @return
     */
    public Integer addShopInfo(ShopInfo shopInfo) {
        return (Integer) getSqlMapClientTemplate().insert("shopInfo.addShopInfo" , shopInfo);
    }


    /**
     * 启用禁用
     * @param map
     * @return
     */
    public Integer changeEnabled(Map<String, Object> map) {
        return getSqlMapClientTemplate().update("shopInfo.changeEnabled" ,map);
    }
}
