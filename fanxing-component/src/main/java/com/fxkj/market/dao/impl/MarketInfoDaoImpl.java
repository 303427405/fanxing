package com.fxkj.market.dao.impl;

import com.fxkj.core.base.BaseDaoImpl;
import com.fxkj.core.base.PageInfo;
import com.fxkj.market.bean.MarketInfoVO;
import com.fxkj.market.dao.MarketInfoDao;
import com.fxkj.market.entity.MarketInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 市场信息
 * Created By zhangyufei on 2018/1/2
 */
@Repository("marketInfoDao")
public class MarketInfoDaoImpl extends BaseDaoImpl<MarketInfo> implements MarketInfoDao{

    /**
     * 市场信息列表
     * @param parameterMap
     * @return
     */
    public PageInfo<MarketInfo> findMarketInfo(Map<String, Object> parameterMap) {
        return getPageByParamMap(new PageInfo<MarketInfo>(),
                "marketInfo.countMarketInfo", "marketInfo.listMarketInfo", parameterMap);
    }

    /**
     * 添加市场
     * @param marketInfo
     * @return
     */
    public Integer addMarketInfo(MarketInfo marketInfo) {

        return (Integer) getSqlMapClientTemplate().insert(
                "marketInfo.addMarketInfo", marketInfo);
    }

    /**
     * 禁／启用
     * @return
     */
    public Integer changeEnabled(Map<String, Object> map) {
        return (Integer) getSqlMapClientTemplate().update("marketInfo.changeEnabled" , map);
    }

    /**
     * 查询市场信息
     * @param id
     * @return
     */
    public MarketInfo getMarketInfoById(Integer id) {
        return (MarketInfo) getSqlMapClientTemplate().queryForObject(
                "marketInfo.getMarketInfoyId", id);
    }

    /**
     * 修改市场信息
     *
     * @param
     * @return
     */
    public Integer updateMarketInfoById(MarketInfo marketInfo) {
        return (Integer) getSqlMapClientTemplate().update("marketInfo.updateMarketInfoById" , marketInfo);
    }


    /**
     * 根据区域code 查询市场信息
     * @param areaCode
     * @return
     */
    public List<MarketInfo> findMarketInfoByCode(String areaCode) {
        return (List<MarketInfo>) getSqlMapClientTemplate().queryForList("marketInfo.findMarketInfoByCode" , areaCode);
    }


}
