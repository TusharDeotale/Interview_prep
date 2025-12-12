package com.interviewprep.DesignPattern.Registry;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

public class SingletonRegistry {
    private static final Map<String, Object> instances = new ConcurrentHashMap<>();

    public static Object getSingleton(String key, Supplier<Object> creator) {
        return instances.computeIfAbsent(key, k -> creator.get());
    }
}
