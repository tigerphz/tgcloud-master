package com.tiger.tgcloud.core.mybatis;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @description:
 * @author: tiger
 * @date: 2018/9/5 23:13
 * @version: V1.0
 * @modified by:
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
