package org.spring.springboot.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.spring.springboot.Util.AspectUtil;
import org.spring.springboot.annotaion.RedisCache;
import org.spring.springboot.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * describle
 *
 * @author Administrator
 * @date 2018-8-24.
 */
@Aspect
@Component
public class RedisCacheAspect {

    private static  final Logger  LOGGER = Logger.getLogger(RedisCacheAspect.class);

    public    static  final  String BIZ_CACHE_REFIX="bug_cache_";

    @Autowired
    private RedisService  redisService;


    @Pointcut(value = "@annotation(org.spring.springboot.annotaion.RedisCache)")
    public void  point(){

    }

    @Around("point()")
    public Object  handle(ProceedingJoinPoint point) throws Throwable {
        Method currentMethod = AspectUtil.getMethod(point);
        //获取操作名称
        RedisCache cache = currentMethod.getAnnotation(RedisCache.class);
        String key = AspectUtil.getKey(point, cache.key(), BIZ_CACHE_REFIX);
        boolean hasKey = redisService.hasKey(key);
        boolean flush = cache.flush();
        if (flush) {
            LOGGER.info("清空缓存"+ BIZ_CACHE_REFIX);
            redisService.del(key);
            return point.proceed();
        }
        if (hasKey) {
            try {
                LOGGER.info("从缓存中获取数据"+key);
                return redisService.get(key);
            } catch (Exception e){
                LOGGER.error("从缓存中获取数据失败！", e);
            }
        }
        //先执行业务
        Object result = point.proceed();
        redisService.set(key, result, cache.expire(), cache.unit());
        LOGGER.info("从数据库中获取数据"+key);
        return result;
    }
}
