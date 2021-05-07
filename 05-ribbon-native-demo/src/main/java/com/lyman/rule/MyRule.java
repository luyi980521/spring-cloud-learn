package com.lyman.rule;

import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.Server;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * Created by luyi on 2021/5/6 12:40.
 * 描述：自定义负载均衡策略
 */
@Slf4j
public class MyRule implements IRule {

    private ILoadBalancer loadBalancer;

    @Override
    public Server choose(Object o) {
        List<Server> serviceList = loadBalancer.getAllServers();
        serviceList.forEach(d -> {
            log.info("主机 + 端口号: {}", d.getHostPort());
        });
        return serviceList.get(0);
    }

    @Override
    public void setLoadBalancer(ILoadBalancer iLoadBalancer) {
        this.loadBalancer = iLoadBalancer;
    }

    @Override
    public ILoadBalancer getLoadBalancer() {
        return loadBalancer;
    }
}
