package com.lyman.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolProperties;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import com.netflix.hystrix.strategy.properties.HystrixPropertiesCommandDefault;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by luyi on 2021/5/7 12:22.
 * 描述：TODO
 */
@Slf4j
public class MyHystrixCommand extends HystrixCommand<String> {

    private final String name;

    public MyHystrixCommand(String name) {
        /**
         * 系统默认采用线程隔离的策略配置，下面分别介绍一下信号量策略和线程隔离策略
         */
//        super(HystrixCommandGroupKey.Factory.asKey("MyGroup"));

        // 1. 信号量策略配置
//        super(HystrixCommand.Setter
//                .withGroupKey(HystrixCommandGroupKey.Factory.asKey("MyGroup"))
//                .andCommandPropertiesDefaults(HystrixPropertiesCommandDefault.Setter()
//                .withExecutionIsolationStrategy(HystrixCommandProperties
//                        .ExecutionIsolationStrategy.SEMAPHORE
//                ))
//        );

        // 2. 线程隔离策略
        super(HystrixCommand.Setter
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey("MyGroup"))
                .andCommandPropertiesDefaults(HystrixPropertiesCommandDefault.Setter()
                .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.THREAD))
                .andThreadPoolPropertiesDefaults(
                        HystrixThreadPoolProperties.Setter()
                        .withCoreSize(10)
                        .withMaxQueueSize(100)
                        .withMaximumSize(100)
                )
        );
        this.name = name;
    }

    /**
     * 具体代码逻辑编写的地方
     *
     * @return
     * @throws Exception
     */
    @Override
    protected String run() throws Exception {
//        Thread.sleep(1000 * 10);
        // 重写了getCacheKey方法后，如果get data 只输出一次，说明用到了缓存
        log.info("get data");
        return this.name + ":" + Thread.currentThread().getName();
    }

    /**
     * 回退的方法
     *
     * @return
     */
    @Override
    protected String getFallback() {
        log.info("失败了");
        return "失败了";
    }

    @Override
    protected String getCacheKey() {
        // 把创建对象传入进来的name作为key
        return String.valueOf(this.name);
//        return null;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // TODO 在使用 getCacheKey 方法时需要先初始化hystrix请求上下文对象
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        // 同步执行命令
        String res = new MyHystrixCommand("lyman").execute();
        System.out.println(res);
        // 异步执行命令
        Future<String> future = new MyHystrixCommand("lyman").queue();
        log.info(future.get());
    }
}
