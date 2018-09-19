package com.tiger.tgcloud.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @description:
 * @author: tiger
 * @date: 2018/8/2 9:06
 * @version: V1.0
 * @modified by:
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BigDecimalUtil {

    /**
     * Add big decimal.
     *
     * @param v1 the v 1
     * @param v2 the v 2
     * @return the big decimal
     */
    public static BigDecimal add(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2);
    }

    /**
     * Sub big decimal.
     *
     * @param v1 the v 1
     * @param v2 the v 2
     * @return the big decimal
     */
    public static BigDecimal sub(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2);
    }


    /**
     * Mul big decimal.
     *
     * @param v1 the v 1
     * @param v2 the v 2
     * @return the big decimal
     */
    public static BigDecimal mul(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2);
    }

    /**
     * Div big decimal.
     *
     * @param v1 the v 1
     * @param v2 the v 2
     * @return the big decimal
     */
    public static BigDecimal div(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, 2, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 多个BigDecimal相加
     * @param args
     * @return
     */
    public static BigDecimal add(BigDecimal... args) {
        BigDecimal value = BigDecimal.valueOf(0);

        for (int i = 0; i < args.length; i++) {
            if (args[i] != null) {
                value = value.add((args[i]));
            }
        }

        return value;
    }

}
