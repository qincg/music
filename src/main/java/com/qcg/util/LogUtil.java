package com.qcg.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @Author: qcg
 * @Description:
 * @Date: 2018/4/24 14:06
 */
public class LogUtil {
    static Logger logger;

    public LogUtil(String logName) {
        logger = LogManager.getLogger(logName);
    }

    public LogUtil() {
        logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);
    }

    public static LogUtil getLogger(String logName) {
        return new LogUtil(logName);
    }

    public static LogUtil getLogger() {
        return new LogUtil();
    }

    public void info(String info) {
        logger.info(info);
    }

    public void trace(String trace) {
        logger.trace(trace);
    }

    public void debug(String debug) {
        logger.debug(debug);
    }

    public void error(String error) {
        logger.error(error);
    }

    public void warn(String warn) {
        logger.warn(warn);
    }

    public static void fatal(String fatal) {
        logger.fatal(fatal);
    }
}
