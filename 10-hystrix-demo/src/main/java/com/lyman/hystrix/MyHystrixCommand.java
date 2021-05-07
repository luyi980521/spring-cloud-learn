package com.lyman.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixKey;
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
        super(HystrixCommandGroupKey.Factory.asKey("MyGroup"));
        this.name = name;
    }

    @Override
    protected String run() throws Exception {
        Thread.sleep(1000 * 10);
        return this.name + ":" + Thread.currentThread().getName();
    }

    @Override
    protected String getFallback() {
        log.info("失败了");
        return "失败了";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 同步执行命令
//        String res = new MyHystrixCommand("lyman").execute();
        // 异步执行命令
        Future<String> future = new MyHystrixCommand("lyman").queue();
        log.info(future.get());
    }
}
