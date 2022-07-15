package com.julscodes.shortener;


import org.slf4j.LoggerFactory;
import org.slf4j.Marker;

import java.util.HashMap;
import java.util.Map;

public class Logger {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(Logger.class);
    private static Map<String, org.slf4j.Logger> pool = new HashMap<>();

    public static void info(String className, String message) {
        if (!pool.containsKey(className)) {
            pool.put(className, LoggerFactory.getLogger(className));
        }
        pool.get(className).info(message);
    }

    public static void debug(String className, String message) {

        if (!pool.containsKey(className)) {
            pool.put(className, LoggerFactory.getLogger(className));
        }
        pool.get(className).debug(message);

    }


}
