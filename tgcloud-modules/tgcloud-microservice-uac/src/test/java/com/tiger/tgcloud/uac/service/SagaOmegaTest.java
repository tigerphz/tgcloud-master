package com.tiger.tgcloud.uac.service;

import com.tiger.tgcloud.TgCloudUacApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @description:
 * @author: tiger
 * @date: 2018/11/8 10:51
 * @version: V1.0
 * @modified by:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TgCloudUacApplication.class)
@AutoConfigureMockMvc
public class SagaOmegaTest {
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void test() {

    }
}
