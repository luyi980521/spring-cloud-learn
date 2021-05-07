package com.lyman.controller;

import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.LoadBalancerBuilder;
import com.netflix.loadbalancer.Server;
import com.netflix.loadbalancer.reactive.LoadBalancerCommand;
import com.netflix.loadbalancer.reactive.ServerOperation;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import rx.Observable;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by luyi on 2021/5/5 18:25.
 * 描述：通过ribbon实现请求负载均衡
 */
@Slf4j
@RestController
public class RibbonController {

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @GetMapping("/ribbon")
    public void ribbonClient() {
        // 服务列表
        List<Server> serverList = Lists.newArrayList(
                new Server("localhost", 8081),
                new Server("localhost", 8083));
        // 构建负载实例
        ILoadBalancer loadBalancer = LoadBalancerBuilder.newBuilder()
                .buildFixedServerListLoadBalancer(serverList);
        // 调用5次来测试效果
        for (int i = 0; i < 5; i++) {
            String res = LoadBalancerCommand.<String>builder()
                    .withLoadBalancer(loadBalancer)
                    .build()
                    .submit(new ServerOperation<String>() {
                        @Override
                        public Observable<String> call(Server server) {
                            try {
                                // 构造请求地址
                                String addr = "http://" + server.getHost() + ":" + server.getPort() + "/user/hello";
                                log.info("调用地址为：{}", addr);
                                URL url = new URL(addr);
                                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                                conn.setRequestMethod("GET");
                                conn.connect();
                                InputStream is = conn.getInputStream();
                                byte[] bytes = new byte[is.available()];
                                is.read(bytes);
                                return Observable.just(new String(bytes));
                            } catch (Exception e) {
                                return Observable.error(e);
                            }
                        }
                    }).toBlocking().first();
            log.info("调用结果：{}", res);
        }
    }

    @GetMapping("/choose")
    public Object chooseUrl() {
        return loadBalancerClient.choose("ribbon-demo");
    }
}
