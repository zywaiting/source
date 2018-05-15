package com.zy.source.utils.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerDemo {
    private static Logger logger = LoggerFactory.getLogger(LoggerDemo.class);

    public static void main(String[] args) {
        System.out.println("This is println message. yeah");

        // 记录debug级别的信息
        logger.debug("This is debug message.");
        // 记录info级别的信息
        logger.info("This is info message.");
        // 记录error级别的信息
        logger.error("This is error message.");
    }
}
