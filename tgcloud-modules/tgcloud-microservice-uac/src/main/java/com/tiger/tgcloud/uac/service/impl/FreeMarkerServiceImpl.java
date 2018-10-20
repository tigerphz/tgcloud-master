package com.tiger.tgcloud.uac.service.impl;

import com.google.common.base.Preconditions;
import com.tiger.tgcloud.uac.service.FreeMarkerService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;

/**
 * @description:
 * @author: tiger
 * @date: 2018/10/19 23:19
 * @version: V1.0
 * @modified by:
 */
@Service
public class FreeMarkerServiceImpl implements FreeMarkerService {
    @Resource
    private Configuration configuration;

    /**
     * 获取FreeMarker模板.
     *
     * @param map              the map
     * @param templateLocation the template location
     * @return the template
     * @throws IOException       the io exception
     * @throws TemplateException the template exception
     */
    @Override
    public String getTemplate(Map<String, Object> map, String templateLocation) throws IOException, TemplateException {
        Preconditions.checkArgument(StringUtils.isNotEmpty(templateLocation), "模板路径不能为空");

        Template t = configuration.getTemplate(templateLocation, "UTF-8");
        return FreeMarkerTemplateUtils.processTemplateIntoString(t, map);
    }
}
