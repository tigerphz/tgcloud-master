package com.tiger.tgcloud.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @description:
 * @author: tiger
 * @date: 2018/8/3 20:36
 * @version: V1.0
 * @modified by:
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NoNeedAccessAuthentication {

}