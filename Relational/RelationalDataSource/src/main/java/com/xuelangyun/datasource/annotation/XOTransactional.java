package com.xuelangyun.datasource.annotation;

import java.lang.annotation.*;

/** @Author mochen.qy @Date 2022/2/15 18:15 @Description: */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface XOTransactional {}
