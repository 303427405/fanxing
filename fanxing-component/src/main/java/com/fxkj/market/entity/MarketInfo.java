package com.fxkj.market.entity;

import com.fxkj.core.base.NoCheckLogin;

/**
 * Created By zhangyufei on 2018/1/2
 */
@SuppressWarnings("serial")
@NoCheckLogin
public class MarketInfo {
    private Integer id;

    /**
     * 市场名称
     */
    private String name;

    /**
     * 市场编号
     */
    private String code;

    /**
     * 市场地址
     */
    private String address;

    /**
     * 市场图
     */
    private String photoMaxUrl;

    /**
     * 所属区域
     */
    private String areaCode;

    /**
     * 是否删除：1启用，0禁用
     */
    private Integer status;

    /**
     * 是否启用：1启用，0禁用
     */
    private Integer enabled;


    /**
     * 地址
     */
    private String areaName;

    /**
     *内容
     */
    private String content;

    /**
     * 备注
     */
    private String remark;

    private String createUser;

    private String createDate;

    private String updateUser;

    private String updateDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhotoMaxUrl() {
        return photoMaxUrl;
    }

    public void setPhotoMaxUrl(String photoMaxUrl) {
        this.photoMaxUrl = photoMaxUrl;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }
}
