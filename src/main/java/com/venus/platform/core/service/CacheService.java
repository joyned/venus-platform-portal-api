package com.venus.platform.core.service;

public interface CacheService {

    void put(String key, Object value);

    Object get(String key);

    void remove(String key);

    void clear();
}
