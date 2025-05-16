package com.ic.agile.annotation;

import java.lang.annotation.*;

/**
 * 登录效验
 * @author zhangzc
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Login {
}
