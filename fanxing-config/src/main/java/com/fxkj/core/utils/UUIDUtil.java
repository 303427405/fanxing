package com.fxkj.core.utils;

import java.util.Random;
import java.util.UUID;

/**
 * 生成uuid工具类
 * @author xukai
 * 
 */
public class UUIDUtil {

    public static String getUUID() {
        String uuid = UUID.randomUUID().toString();
        return uuid.toUpperCase().replace("-", "");
    }


    /**
     * Created by lwh on 2016/4/6.
     */
    public static class RandomVerifyCode {

        /**
         * 生成六位随机验证码
         * @return
         */
        public static String getVerifyCode() {
            Random random = new Random();
            int num = random.nextInt(899999)+100000;
            return String.valueOf(num);
        }
    }
}