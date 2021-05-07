package com.lyman.intercepter;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerRequestFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.Assert;

import java.io.IOException;
import java.net.URI;

/**
 * Created by luyi on 2021/5/5 19:59.
 * 描述：自定义拦截器，证明@LoadBalanced注解是通过拦截请求，
 * 然后将请求的服务地址更换为服务名
 */
@Slf4j
public class MyLoadBalancerInterceptor implements ClientHttpRequestInterceptor {

    private LoadBalancerClient loadBalancer;
    private LoadBalancerRequestFactory requestFactory;

    public MyLoadBalancerInterceptor() {
    }

    public MyLoadBalancerInterceptor(LoadBalancerClient loadBalancer, LoadBalancerRequestFactory requestFactory) {
        this.loadBalancer = loadBalancer;
        this.requestFactory = requestFactory;
    }

    public MyLoadBalancerInterceptor(LoadBalancerClient loadBalancer) {
        this.loadBalancer = loadBalancer;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body,
                                        ClientHttpRequestExecution execution) throws IOException {
        final URI originalUri = request.getURI();
        String serviceName = originalUri.getHost();
        log.info("进入自定义请求拦截器中: {}", serviceName);
        Assert.state(serviceName != null, "请求的uri中不包含有效的主机名: " + originalUri);
        return this.loadBalancer.execute(serviceName,
                requestFactory.createRequest(request, body, execution));
    }
}
