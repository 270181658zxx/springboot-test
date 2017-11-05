package com.longmao.girl.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


@Aspect
@Component
//AOP 日志处理
public class HttpAspect {
    //spring自带的日志处理
    private final static org.slf4j.Logger logger =
            LoggerFactory.getLogger(HttpAspect.class);

    //@Pointcut提取@Before and @After 路径的重复代码
    @Pointcut("execution(public * " +              //..任何参数都会被拦截
            "com.longmao.girl.controller.GirlController.*(..))")//*为任何方法
    public void log(){
    }
    @Before("log()")
    public void doBefore(JoinPoint joinPoint){
        //获取request请求信息
       ServletRequestAttributes attributes=
               (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest  request = attributes.getRequest();
        //url
        logger.info("url={}", request.getRequestURL());
        //method
        logger.info("method={}",request.getMethod());
        //ip
        logger.info("ip={}",request.getRemoteAddr());
        //类方法//反射机制实现
        logger.info("class_mehHod={}",
                joinPoint.getSignature().getDeclaringTypeName()+"."+
                        joinPoint.getSignature().getName());
        //参数
        logger.info("age={}",joinPoint.getArgs());
    }
    @After("log()")
    private void doAfter(){
         logger.info("222222");
    }
    //获取数据库返回数据信息
    @AfterReturning(returning = "object",pointcut = "log()")
     public void doAfterReturning(Object object){//object由这开始
        // 表单验证 money的message 为空 则object抛出异常
        //object的toString方法就会抛出空指针异常
       logger.info("response={}",object.toString());
    }
}

