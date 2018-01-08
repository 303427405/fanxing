package com.fxkj.enums;

/**
 * 商家类型
 * Created By zhangyufei on 2018/1/5
 */
public enum ShopTypeEnum {

    /**供应商*/
    SUPPLIER(1),

    /**采购商*/
    PURCHASERS(0);

    private Integer code;


    ShopTypeEnum(Integer code) {
        this.code = code;
    }

    public static ShopTypeEnum getShopType(Integer shopType) {
        switch(shopType) {
            case 0:
                return SUPPLIER;
            case 1:
                return PURCHASERS;
        }
        return null;
    }

    public Integer getCode() {
        return code;
    }






}
