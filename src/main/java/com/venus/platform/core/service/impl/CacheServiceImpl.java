package com.venus.platform.core.service.impl;

import com.venus.platform.core.service.CacheService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CacheServiceImpl implements CacheService {

    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public void put(String key, Object value) {
        redisTemplate.opsForValue().set(key, value.toString());
    }

    @Override
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public void remove(String key) {
        redisTemplate.opsForValue().getAndDelete(key);
    }

    @Override
    public void clear() {
        redisTemplate.opsForValue().getAndDelete("*");
    }
}
