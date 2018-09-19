package com.tiger.tgcloud.core.aspect;

import com.tiger.tgcloud.utils.ThreadLocalMap;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: tiger
 * @date: 2018/8/3 22:44
 * @version: V1.0
 * @modified by:
 */
@Aspect
@Component
public class NotDisplaySqlAspect {
    /**
     * The constant DISPLAY_SQL.
     */
    public static final String DISPLAY_SQL = "DISPLAY_SQL";

    @Pointcut("@annotation(com.tiger.tgcloud.core.annotation.NotDisplaySql)")
    private void myPointCut() {
    }

    /**
     * Before.
     */
    @Before(value = "myPointCut()")
    public void before() {
        ThreadLocalMap.put(DISPLAY_SQL, Boolean.FALSE);
    }

    /**
     * After.
     */
    @After(value = "myPointCut()")
    public void after() {
        ThreadLocalMap.remove(DISPLAY_SQL);
    }
}
