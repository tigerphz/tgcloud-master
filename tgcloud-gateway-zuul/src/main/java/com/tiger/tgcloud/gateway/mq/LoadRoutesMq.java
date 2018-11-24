package com.tiger.tgcloud.gateway.mq;

import com.tiger.tgcloud.gateway.route.CustomDiscoveryClientRouteLocator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.discovery.DiscoveryClientRouteLocator;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: tiger
 * @date: 2018/11/22 10:29
 * @version: V1.0
 * @modified by:
 */
@Component
@RabbitListener(queues = "refreshRoutes")
@Slf4j
public class LoadRoutesMq {
    @Autowired
    private DiscoveryClientRouteLocator discoveryClientRouteLocator;

    @RabbitHandler
    public void process(String msg) {
        log.info("Receiver topic_refreshroutes mq :" + msg);

        if (discoveryClientRouteLocator instanceof CustomDiscoveryClientRouteLocator) {
            CustomDiscoveryClientRouteLocator customDiscoveryClientRouteLocator =
                    (CustomDiscoveryClientRouteLocator) discoveryClientRouteLocator;
            customDiscoveryClientRouteLocator.loadLocateRoutesFromDB();
        }
    }
}
