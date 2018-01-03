package com.fxkj.market.bean;

import com.fxkj.core.base.BaseSearch;

/**
 * 市场查询条件
 * Created By zhangyufei on 2018/1/2
 */
public class MarketInfoSearcher extends BaseSearch {

    private String name;
    private Integer enabled;// 状态 1：启用 0：禁用

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }
}
