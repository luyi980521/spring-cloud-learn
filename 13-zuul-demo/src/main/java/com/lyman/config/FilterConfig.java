package com.lyman.config;

import com.lyman.zuul.ErrorFilter;
import com.lyman.zuul.IpFilter;
import com.lyman.zuul.SecondFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by luyi on 2021/5/9 19:38.
 * 描述：TODO
 */
@Configuration
public class FilterConfig {

    @Bean
    public IpFilter ipFilter() {
        return new IpFilter();
    }

    @Bean
    public SecondFilter secondFilter() {
        return new SecondFilter();
    }

    @Bean
    public ErrorFilter errorFilter() {
        return new ErrorFilter();
    }
}
