package org.spring.springboot.annotaion;

import org.spring.springboot.aspect.RedisTypeEnum;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * describle
 *
 * @author Administrator
 * @date 2018-8-24.
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface RedisCache {

    /**
     * 业务的名称
     * @return
     */
    String  value()  default "";


    /***
     * redis缓存的key（默认方法名称-方法名-自定义key）
     * @return
     */
    String  key()   default "";

    /**
     * 是否刷新，默认false
     * @return
     */
    boolean  flush() default   false;

    /**
     * 缓存失效时间，默认30
     * @return
     */
    int  expire() default   30;

    /**
     * 默认时间单位，默认天
     * @return
     */
    TimeUnit  unit()   default  TimeUnit.DAYS;

    /**
     * 存放类型
     * @return
     */
    RedisTypeEnum  type()   default RedisTypeEnum.STRING;
}
