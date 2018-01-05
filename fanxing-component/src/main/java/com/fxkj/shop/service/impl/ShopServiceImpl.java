package com.fxkj.shop.service.impl;

import com.fxkj.core.base.BaseService;
import com.fxkj.core.base.MsgUtil;
import com.fxkj.core.base.PageInfo;
import com.fxkj.core.common.Commons;
import com.fxkj.core.common.SecurityUtils;
import com.fxkj.core.utils.DateUtils;
import com.fxkj.core.utils.StringUtil;
import com.fxkj.enums.EnabledEnum;
import com.fxkj.productCategory.service.ProductCategoryService;
import com.fxkj.shop.bean.ShopSearch;
import com.fxkj.shop.dao.ShopDao;
import com.fxkj.shop.entity.ShopInfo;
import com.fxkj.shop.service.ShopService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * 商户信息
 * Created By zhangyufei on 2018/1/3
 */
@Service("shopService")
@Transactional
public class ShopServiceImpl extends BaseService implements
        ShopService {

    @Autowired
    private ShopDao shopDao;


    /**
     * 分页查询商户信息
     * @param search
     * @param parameterMap
     * @return
     */
    public PageInfo<ShopInfo> findShopInfo(ShopSearch search, Map<String, Object> parameterMap) {
        if (StringUtil.isEmpty(search.getOrgCode())) {
            String orgCode = SecurityUtils.getCurrentOperator().getOrgCode();
            if (!orgCode.startsWith(Commons.COUNTRYCODE)) {
                search.setOrgCode(orgCode);
            }
        }
        parameterMap.put("ps", search);
        return shopDao.findShopInfo(parameterMap);
    }


    /**
     * 添加商户信息
     * @param shopInfo
     * @return
     */
    public MsgUtil addShopInfo(ShopInfo shopInfo) {
        if (shopInfo != null && shopInfo.getAreaCode() != null) {
            shopInfo.setCode(shopInfo.getAreaCode()+"_"+ DateUtils.longDate());
            shopInfo.setStatus(EnabledEnum.ENABLED.getCode());
            int temp = shopDao.addShopInfo(shopInfo);
            return new MsgUtil(temp > 0 ? true : false, temp > 0 ? SUCCESS_MSG
                    : ERROR_MSG);
        } else {
            return new MsgUtil(false, PARAM_MSG);
        }
    }

    /**
     * 启用禁用
     * @param id
     * @return
     */
    public MsgUtil changeEnabled(Integer id,Boolean flg) {
        if (id != null && flg != null) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", id);
            map.put("flg", flg);
            int temp = shopDao.changeEnabled(map);
            return new MsgUtil(temp > 0 ? true : false, temp > 0 ? SUCCESS_MSG
                    : ERROR_MSG);
        } else {
            return new MsgUtil(false, PARAM_MSG);
        }
    }
}
