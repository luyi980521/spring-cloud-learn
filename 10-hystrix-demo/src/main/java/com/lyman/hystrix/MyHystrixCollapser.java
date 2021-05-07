package com.lyman.hystrix;

import com.netflix.hystrix.HystrixCollapser;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by luyi on 2021/5/7 20:53.
 * 描述：通过合并请求可以将多次合并为1次，从而节省网络开销
 */
@Slf4j
public class MyHystrixCollapser extends HystrixCollapser<List<String>, String, String> {

    private final String name;

    public MyHystrixCollapser(String name) {
        this.name = name;
    }

    @Override
    public String getRequestArgument() {
        return name;
    }

    @Override
    protected HystrixCommand<List<String>> createCommand(
            Collection<CollapsedRequest<String, String>> collapsedRequests) {
        return new BatchCommand(collapsedRequests);
    }

    @Override
    protected void mapResponseToRequests(List<String> batchResponse,
                                         Collection<CollapsedRequest<String, String>> collapsedRequests) {
        int count = 0;
        for (CollapsedRequest<String, String> request : collapsedRequests) {
            request.setResponse(batchResponse.get(count++));
        }
    }

    private static class BatchCommand extends HystrixCommand<List<String>> {

        private final Collection<CollapsedRequest<String, String>> requests;

        private BatchCommand(Collection<CollapsedRequest<String, String>> requests) {
            super(HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"))
            .andCommandKey(HystrixCommandKey.Factory.asKey("GetValueForKey")));
            this.requests = requests;
        }

        @Override
        protected List<String> run() throws Exception {
            log.info("真正执行的请求");
            List<String> response = new ArrayList<>();
            for (CollapsedRequest<String, String> request : requests) {
                response.add("返回结果: " + request.getArgument());
            }
            return response;
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        Future<String> f1 = new MyHystrixCollapser("lyman").queue();
        Future<String> f2 = new MyHystrixCollapser("lyman123").queue();
        log.info(f1.get() + "---" + f2.get());
        context.shutdown();
    }
}
