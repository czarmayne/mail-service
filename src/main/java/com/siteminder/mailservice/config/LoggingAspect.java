package com.siteminder.mailservice.config;

import com.siteminder.mailservice.core.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Aspect
@Configuration
@Slf4j
@EnableAspectJAutoProxy
public class LoggingAspect {

    @Around("@annotation(com.siteminder.mailservice.core.annotation.LogExecutionTime)")
    public Object methodExecutionTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();

        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();

        long startTime = System.nanoTime();
        Object result = proceedingJoinPoint.proceed();
        long endTime = System.nanoTime();
        long duration = DateUtil.getDuration(startTime, endTime);

        log.info("[Execution Duration] {}.{}: {} ms", className, methodName, duration);
        return result;
    }
}
