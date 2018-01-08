package com.fxkj.media.enums;

import java.io.File;

/**
 * Created By zhangyufei on 2018/1/5
 */
public enum MediaType {

    SHOPINFO_ID_CARD_FACE(1,"商户信息—身份证正面图片","img" + File.separator + "shop/id_card_face" + File.separator),

    SHOPINFO_ID_CARD_BACK(2,"商户信息—身份证反面图片","img" + File.separator + "shop/id_card_back" + File.separator),

    SHOPINFO_MIN_LOGO(3,"商户信息—商户小图","img" + File.separator + "shop/min_logo" + File.separator),

    SHOPINFO_BIG_LOGO(4,"商户信息—商户大图","img" + File.separator + "shop/big_logo" + File.separator),

    SHOPINFO_FOOD_LICENSE(5,"商户信息—食品许可证","img" + File.separator + "shop/food_license" + File.separator),

    SHOPINFO_BUSINESS_LICENSE(6,"商户信息—营业许可证许可证","img" + File.separator + "shop/business_license" + File.separator),

    SHOPINFO_OTHER_ENCLOSURE(7,"商户信息—其他附件","img" + File.separator + "shop/other_enclosure" + File.separator);


    private Integer code;
    private String name;
    private String path;

    MediaType(Integer code, String name, String path){
        this.code=code;
        this.name=name;
        this.path=path;
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }


}
