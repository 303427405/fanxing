package com.fxkj.core.base;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 进行实体操作（add,update）不校验用户是否已经登录
 * @author haiyangtao
 *
 */

@Target( {ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NoCheckLogin {

}
