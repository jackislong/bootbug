package org.spring.springboot.service;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * describle
 *
 * @author Administrator
 * @date 2018-8-24.
 */
public interface RedisService {
    <T> void set(String key, T value);

    <T> void set(String key, T value, int expire,TimeUnit timeUnit);

    Object get(String key);

    long expire(String key, int expire);

    void del(String key);

    void delBatch(Set<String> keys);

    boolean hasKey(String key);


}
