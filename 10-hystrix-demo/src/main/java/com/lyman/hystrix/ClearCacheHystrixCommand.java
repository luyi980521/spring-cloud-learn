package com.lyman.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixRequestCache;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategyDefault;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by luyi on 2021/5/7 20:26.
 * 描述：使用了Hystrix的缓存功能后，当数据发生变化时，
 * 为了避免脏数据，所以需要清除缓存
 */
@Slf4j
public class ClearCacheHystrixCommand extends HystrixCommand<String> {

    private final String name;
    private static final HystrixCommandKey GETTER_KEY =
            HystrixCommandKey.Factory.asKey("MyKey");

    public ClearCacheHystrixCommand(String name) {
        super(HystrixCommand.Setter.withGroupKey(
                HystrixCommandGroupKey.Factory.asKey("MyGroup"))
                .andCommandKey(GETTER_KEY)
        );
        this.name = name;
    }

    /**
     * 刷新缓存
     *
     * @return
     * @throws Exception
     */
    public static void flushCache(String name) {
        HystrixRequestCache.getInstance(
                GETTER_KEY,
                HystrixConcurrencyStrategyDefault.getInstance()
        ).clear(name);
        log.info("remove from cache {}", name);
    }

    /**
     * 从缓存中获取数据
     *
     * @return
     */
    @Override
    protected String getCacheKey() {
        log.info("get data to cache, value: {}", this.name);
        return String.valueOf(this.name);
    }

    /**
     * 具体代码逻辑
     *
     * @return
     * @throws Exception
     */
    @Override
    protected String run() throws Exception {
        log.info("get data");
        return this.name + ":" + Thread.currentThread().getName();
    }

    /**
     * 回退后的执行方法
     *
     * @return
     */
    @Override
    protected String getFallback() {
        log.info("execute failed, begin fallback");
        return "execute failed, begin fallback";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /**
         * 验证缓存清空之后还会再打印一次 get data
         */
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        String resFromCache = new ClearCacheHystrixCommand("lyman").execute();
        log.info(resFromCache);
        flushCache("lyman");
        Future<String> future = new ClearCacheHystrixCommand("lyman").queue();
        log.info(future.get());
    }
}
