package com.fxkj.market.dao;

import com.fxkj.core.base.PageInfo;
import com.fxkj.market.entity.MarketInfo;

import java.util.Map;

/**
 * Created By zhangyufei on 2018/1/2
 */
public interface MarketInfoDao {

    /**
     * 市场信息列表
     *
     * @param parameterMap
     * @return
     */
    PageInfo<MarketInfo> findMarketInfo(Map<String, Object> parameterMap);

    /**
     * 添加市场
     * @param marketInfo
     * @return
     */
    Integer addMarketInfo(MarketInfo marketInfo);

    /**
     * 禁／启用
     */
    Integer changeEnabled(Map<String, Object> map);


    /**
     * 查询市场信息
     * @param id
     * @return
     */
    MarketInfo getMarketInfoById(Integer id);


    /**
     * 修改市场信息
     *
     * @param
     * @return
     */
    Integer updateMarketInfoById(MarketInfo marketInfo);









}
