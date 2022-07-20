package com.roxy.blog.aspect;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;

/**
 * 日志切面类
 */
@Slf4j
@Aspect
@Component
public class LogAspect {

    @Pointcut("execution(public * com.roxy.blog.controller..*.*(..))")//切入点描述 这个是controller包的切入点
    public void controllerLog() {
    }

    //统计请求的处理时间
    ThreadLocal<Long> startTime = new ThreadLocal<>();

    //在切入点的方法run之前要干的
    @Before("controllerLog()")
    public void logBeforeController(JoinPoint joinPoint) {

        //这个RequestContextHolder是Springmvc提供来获得请求的东西
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();

        // 记录下请求内容
        log.info("######### URL : " + request.getRequestURL().toString());
        log.info("######### HTTP_METHOD : " + request.getMethod());
        log.info("######### IP : " + request.getRemoteAddr());
        log.info("######### THE ARGS OF THE CONTROLLER : " + Arrays.toString(joinPoint.getArgs()));

        //getSignature().getDeclaringTypeName()获取包+类名  joinPoint.getSignature.getName()获取方法名
        log.info("######### CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());

        log.info("######### request time : {}", new Date());
        startTime.set(System.currentTimeMillis());
    }

    @AfterReturning(returning = "ret", pointcut = "controllerLog()")
    public void doAfterReturning(Object ret) {
        //处理完请求后，返回内容
        log.info("######### return message : {}", JSON.toJSONString(ret));
        log.info("######### execution time : {}", System.currentTimeMillis() - startTime.get());
    }

    @AfterThrowing(throwing = "thr", pointcut = "controllerLog()")
    public void doAfterThrowing(Throwable thr) {
        //处理完请求后，返回内容
        log.warn("######### throw message : {}", thr.getMessage());
        log.info("######### execution time : {}", System.currentTimeMillis() - startTime.get());
        startTime.remove();
    }
}
