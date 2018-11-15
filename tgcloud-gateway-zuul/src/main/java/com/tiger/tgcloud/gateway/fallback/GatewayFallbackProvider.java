package com.tiger.tgcloud.gateway.fallback;

import com.netflix.hystrix.exception.HystrixTimeoutException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * @description: Zuul作为服务网关为了保证自己不被服务拖垮，本身已经集成了Hystrix对路由转发进行隔离。
 * 为了方便开发人员对服务短路进行自定义处理，
 * Zuul 提供了 ZuulFallbackProvider 接口，开发人员可以通过实现该接口来完成自定义Hystrix Fallback
 * Spring Cloud Zuul 提供了 FallbackProvider替代了ZuulFallbackProvider接口。因此我们实现FallbackProvider即可
 * @author: tiger
 * @date: 2018/9/14 20:30
 * @version: V1.0
 * @modified by:
 */
@Component
@Slf4j
public class GatewayFallbackProvider implements FallbackProvider {


    @Override
    public String getRoute() {
        return "*";
    }

    @Override
    public ClientHttpResponse fallbackResponse(String route, final Throwable cause) {
        if (cause instanceof HystrixTimeoutException) {
            return response(HttpStatus.GATEWAY_TIMEOUT);
        } else {
            return fallbackResponse();
        }
    }

    public ClientHttpResponse fallbackResponse() {
        return response(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ClientHttpResponse response(final HttpStatus status) {
        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() {
                return status;
            }

            @Override
            public int getRawStatusCode() {
                return status.value();
            }

            @Override
            public String getStatusText() {
                return status.getReasonPhrase();
            }

            @Override
            public void close() {
                log.info("close");
            }

            @Override
            public InputStream getBody() {
                String message = "{\n" +
                        "\"code\": 200,\n" +
                        "\"message\": \"微服务故障, 请稍后再试\"\n" +
                        "}";
                return new ByteArrayInputStream(message.getBytes());
            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                return headers;
            }
        };
    }
}
