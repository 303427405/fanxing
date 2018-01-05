package com.fxkj.shop.service;

import com.fxkj.core.base.MsgUtil;
import com.fxkj.core.base.PageInfo;
import com.fxkj.shop.bean.ShopSearch;
import com.fxkj.shop.entity.ShopInfo;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.Map;

/**
 * Created By zhangyufei on 2018/1/3
 */
public interface ShopService {

    /**
     * 商户信息列表
     *
     * @param parameterMap
     * @return
     */
    PageInfo<ShopInfo> findShopInfo(ShopSearch search, Map<String, Object> parameterMap);

    /**
     * 添加商户
     * @param shopInfo
     * @return
     */
    MsgUtil addShopInfo(ShopInfo shopInfo);


    /**
     * 启用禁用
     * @param id
     * @return
     */
    MsgUtil changeEnabled(Integer id , Boolean flg);

}
