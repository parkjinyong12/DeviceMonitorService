package com.ruokit.device.spring.config;

import com.ruokit.device.monitor.controller.device.DeviceController;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Aspect
@Component
public class DeviceMonitorAopConfig {

    private final static Logger logger = LoggerFactory.getLogger(DeviceMonitorAopConfig.class);

    @Around("execution(* com.ruokit.device.monitor.service..*Service.*(..))")
    public Object checkService(ProceedingJoinPoint service) throws Throwable {

        long start = System.currentTimeMillis();

        String fullClassName = service.getSignature().getDeclaringTypeName();
        String className = fullClassName.substring(fullClassName.lastIndexOf(".")+1);

        logger.info(service.getSignature().getName()+ "() in " + className);
        Object result = service.proceed();

        long end = System.currentTimeMillis();
        logger.info(service.getSignature().getName() + "() " +(end-start) + "ms 소요");
        return result;
    }

    @Around("execution(* com.ruokit.device.monitor.controller..*Controller.*(..))")
    public Object checkController(ProceedingJoinPoint service) throws Throwable {

        long start = System.currentTimeMillis();

        String fullClassName = service.getSignature().getDeclaringTypeName();
        String className = fullClassName.substring(fullClassName.lastIndexOf(".")+1);

        logger.info(service.getSignature().getName()+ "() in " + className);
        Object result = service.proceed();

        long end = System.currentTimeMillis();
        logger.info(service.getSignature().getName() + "() " +(end-start) + "ms 소요");
        return result;
    }
}
