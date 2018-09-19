package com.tiger.tgcloud.core.annotation;

import java.lang.annotation.*;

/**
 * @description: 配合 SqlLogInterceptor 对指定方法 禁止打印SQL到控制台
 * @author: tiger
 * @date: 2018/8/3 20:21
 * @version: V1.0
 * @modified by:
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface NotDisplaySql {
}
