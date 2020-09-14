package com.learn.jeffrey.config;

import org.springframework.context.annotation.Import;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Description <P></P>
 *
 * @Author lijianfei
 * @Date 2020/9/14 17:22
 **/
@Documented
@Retention(RUNTIME)
@Target(ElementType.TYPE)
@Import({ZookeeperConfig.class})
public @interface EnableZookeeper {

}
