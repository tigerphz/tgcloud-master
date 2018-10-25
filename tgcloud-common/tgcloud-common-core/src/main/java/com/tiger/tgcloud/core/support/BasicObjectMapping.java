package com.tiger.tgcloud.core.support;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * @description:
 * @author: tiger
 * @date: 2018/10/24 17:39
 * @version: V1.0
 * @modified by:
 */
public interface BasicObjectMapping<SOURCE, TARGET> {
    @Mappings({})
    @InheritConfiguration
    TARGET to(SOURCE var1);

    @InheritConfiguration
    List<TARGET> to(List<SOURCE> var1);

    @InheritInverseConfiguration
    SOURCE from(TARGET var1);

    @InheritInverseConfiguration
    List<SOURCE> from(List<TARGET> var1);
}
