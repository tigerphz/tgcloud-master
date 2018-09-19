package com.tiger.tgcloud.security.core.social.view;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tiger.tgcloud.utils.wrapper.WrapMapper;
import com.tiger.tgcloud.utils.wrapper.Wrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 绑定结果视图
 *
 * @author
 */
public class PcConnectView extends AbstractView {

    private static final String CONNECTIONS = "connections";

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * Render merged output model.
     *
     * @param model    the model
     * @param request  the request
     * @param response the response
     * @throws Exception the exception
     */
    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
                                           HttpServletResponse response) throws Exception {

        response.setContentType("application/json;charset=UTF-8");

        Wrapper wrapper;
        if (model.get(CONNECTIONS) == null) {
            wrapper = WrapMapper.ok("解绑成功");
        } else {
            wrapper = WrapMapper.ok("绑定成功");
        }

        response.getWriter().write(objectMapper.writeValueAsString(wrapper));
    }

}
