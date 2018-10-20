package com.tiger.tgcloud.uac.service;

import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.Map;

/**
 * @description:
 * @author: tiger
 * @date: 2018/10/19 23:16
 * @version: V1.0
 * @modified by:
 */
public interface FreeMarkerService {

    /**
     * 获取FreeMarker模板.
     *
     * @param map              the map
     * @param templateLocation the template location
     * @return the template
     * @throws IOException       the io exception
     * @throws TemplateException the template exception
     */
    String getTemplate(Map<String, Object> map, String templateLocation) throws IOException, TemplateException;
}
