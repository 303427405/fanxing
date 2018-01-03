package com.fxkj.market.service;

import com.fxkj.core.base.MsgUtil;
import com.fxkj.core.base.PageInfo;
import com.fxkj.dictionary.entity.Dictionary;
import com.fxkj.market.entity.MarketInfo;

import java.util.Map;

/**
 * 市场信息
 * Created By zhangyufei on 2018/1/2
 */
public interface MarketInfoService {

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
    MsgUtil addMarketInfo(MarketInfo marketInfo);


    /**
     * 改变市场状态
     *
     * @param id
     * @param flg
     * @return
     */
    MsgUtil changeEnabled(Integer id, Boolean flg);


    /**
     * 查询市场信息
     * @param id
     * @return
     */
    MarketInfo getMarketInfoById(Integer id);


    /**
     * 修改市场信息
     *
     * @return
     */
    MsgUtil updateMarketInfoById(MarketInfo marketInfo);


}
