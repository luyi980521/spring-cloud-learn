package com.lyman.annotation;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.*;

/**
 * Created by luyi on 2021/5/5 20:10.
 * 描述：自定义负载均衡注解
 */
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Qualifier
public @interface MyLoadBalanced {
}
