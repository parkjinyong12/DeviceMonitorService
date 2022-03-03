package com.ruokit.device.monitor.service.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConvertUtilService {

    private static Logger logger = LoggerFactory.getLogger(ConvertUtilService.class);

    public static Long castingToLong(String value) {
        Long valueToLong = null;
        try{
            valueToLong = Long.valueOf(value);
        } catch (NumberFormatException e) {
            logger.info("String -> Long 형변환 오류. value: " + value);
            throw new NumberFormatException();
        }
        return valueToLong;
    }
}
