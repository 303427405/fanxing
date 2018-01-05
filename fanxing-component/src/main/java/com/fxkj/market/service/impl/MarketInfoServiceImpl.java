package com.fxkj.market.service.impl;

import com.fxkj.core.base.BaseService;
import com.fxkj.core.base.MsgUtil;
import com.fxkj.core.base.PageInfo;
import com.fxkj.core.utils.BeanUtil;
import com.fxkj.core.utils.DateUtils;
import com.fxkj.dictionary.bean.DictionaryBean;
import com.fxkj.enums.EnabledEnum;
import com.fxkj.market.bean.MarketInfoVO;
import com.fxkj.market.dao.MarketInfoDao;
import com.fxkj.market.entity.MarketInfo;
import com.fxkj.market.service.MarketInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 市场信息
 * Created By zhangyufei on 2018/1/2
 */
@Service("marketInfoService")
@Transactional
public class MarketInfoServiceImpl  extends BaseService implements MarketInfoService{

    @Autowired
    private MarketInfoDao marketInfoDao;

    /**
     * 市场信息列表
     * @param parameterMap
     * @return
     */
    public PageInfo<MarketInfo> findMarketInfo(Map<String, Object> parameterMap) {
        return marketInfoDao.findMarketInfo(parameterMap);
    }

    /**
     * 添加市场
     * @param marketInfo
     * @return
     */
    public MsgUtil addMarketInfo(MarketInfo marketInfo) {

        if (marketInfo != null && marketInfo.getAreaCode() != null) {
            marketInfo.setCode(marketInfo.getAreaCode()+"_"+ DateUtils.longDate());
            marketInfo.setStatus(EnabledEnum.ENABLED.getCode());
            int temp = marketInfoDao.addMarketInfo(marketInfo);
            return new MsgUtil(temp > 0 ? true : false, temp > 0 ? SUCCESS_MSG
                    : ERROR_MSG);
        } else {
            return new MsgUtil(false, PARAM_MSG);
        }

    }

    /**
     * 改变市场状态
     * @param id
     * @param flg
     * @return
     */
    public MsgUtil changeEnabled(Integer id, Boolean flg) {
        if (id != null && flg != null) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", id);
            map.put("flg", flg);
            int temp = marketInfoDao.changeEnabled(map);
            return new MsgUtil(temp > 0 ? true : false, temp > 0 ? SUCCESS_MSG
                    : ERROR_MSG);
        } else {
            return new MsgUtil(false, PARAM_MSG);
        }
    }

    /**
     * 查询市场信息
     * @param id
     * @return
     */
    public MarketInfo getMarketInfoById(Integer id) {
        return marketInfoDao.getMarketInfoById(id);
    }

    /**
     * 修改市场信息
     * @param marketInfo
     * @return
     */
    public MsgUtil updateMarketInfoById(MarketInfo marketInfo) {
        if (marketInfo.getId() != null) {
            int temp = marketInfoDao.updateMarketInfoById(marketInfo);
            return new MsgUtil(temp > 0 ? true : false, temp > 0 ? SUCCESS_MSG
                    : ERROR_MSG);
        } else {
            return new MsgUtil(false, PARAM_MSG);
        }
    }

    /**
     * 根据区域code 查询市场信息
     * @param areaCode
     * @return
     */
    public List<MarketInfoVO> findMarketInfoByCode(String areaCode) {
        List<MarketInfo> marketInfos = marketInfoDao.findMarketInfoByCode(areaCode);
        List<MarketInfoVO> marketInfoVOS =  BeanUtil.mapList(marketInfos,
                MarketInfoVO.class);
        return marketInfoVOS;
    }
}
