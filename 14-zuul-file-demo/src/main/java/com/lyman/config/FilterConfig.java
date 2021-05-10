package com.lyman.config;

import com.lyman.zuul.LogFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by luyi on 2021/5/10 21:28.
 * 描述：TODO
 */
@Configuration
public class FilterConfig {

    @Bean
    public LogFilter logFilter() {
        return new LogFilter();
    }
}
