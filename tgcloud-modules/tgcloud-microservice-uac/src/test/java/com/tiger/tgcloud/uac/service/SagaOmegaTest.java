package com.tiger.tgcloud.uac.service;

import com.tiger.tgcloud.TgCloudUacApplication;
import com.tiger.tgcloud.core.security.SnowflakeIdWorker;
import com.tiger.tgcloud.dmc.api.service.MqMessageFeignApi;
import com.tiger.tgcloud.uac.repository.DepartmentRepository;
import org.junit.Before;
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
//@EnableOmega
public class SagaOmegaTest {
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Autowired
    private MqMessageFeignApi mqMessageFeignApi;

    @Autowired
    private DepartmentRepository departmentRepository;

//    @Autowired
//    private OmegaContext omegaContext;

    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;

//    @Test
//    public void SagaOmega() {
//        Long id = snowflakeIdWorker.nextId();
//        String msg = "SagaOmegaTest测试分布式事务";
//
//        sendMessage(id, msg);
//    }

//    @SagaStart(timeout = 10)
//    public void sendMessage(Long id, String msg) {
//        recevieMessage(id, msg);
//
//        MqMessageData data = new MqMessageData();
//        data.setId(id);
//        data.setMessageBody(msg);
//
//        mqMessageFeignApi.saveAndSendMqMessage(data);
//    }
//
//    @Compensable(timeout = 5, compensationMethod = "cancel")
//    public void recevieMessage(Long id, String msg) {
//        DepartmentInfo departmentInfo = new DepartmentInfo();
//        departmentInfo.setId(id);
//        departmentInfo.setDeptname(msg);
//        departmentRepository.save(departmentInfo);
//
//        System.out.print("globalTxId:" + omegaContext.globalTxId() + "localTxId:" + omegaContext.localTxId());
//    }

    public void cancel(Long id, String msg) {
        System.out.print("cancel=id:" + id + "msg:" + msg);
    }
}
