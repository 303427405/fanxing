package com.fxkj.shop.dao;

import com.fxkj.core.base.PageInfo;
import com.fxkj.shop.entity.ShopInfo;

import java.util.Map;

/**
 * Created By zhangyufei on 2018/1/3
 */
public interface ShopDao {

    /**
     * 商户信息列表
     *
     * @param parameterMap
     * @return
     */
    PageInfo<ShopInfo> findShopInfo(Map<String, Object> parameterMap);


    /**
     * 添加商户
     * @param shopInfo
     * @return
     */
    Integer addShopInfo(ShopInfo shopInfo);


    /**
     * 禁／启用
     */
    Integer changeEnabled(Map<String, Object> map);
}
