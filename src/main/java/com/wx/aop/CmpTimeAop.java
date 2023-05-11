//package com.wx.aop;
//
//import com.wx.utils.DateUtil;
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.Signature;
//import org.aspectj.lang.annotation.*;
//import org.springframework.stereotype.Component;
//
//import java.util.Date;
//
//@Aspect
//@Component
//@Slf4j
//public class CmpTimeAop {
//    //切入点表达式决定了用注解方式的方法切还是针对某个路径下的所有类和方法进行切，方法必须是返回void类型
//    @Pointcut(value = "@annotation(com.wx.annotation.CmpTime)")
//    private void CmpTime(){};
//    private Long startTime;
//    private Long endTime;
//
//    private String className;
//    private String methodName;
//    @Before("CmpTime()")
//    public void before(JoinPoint pjp) throws Throwable{
//        log.info("====================进入AOP============================");
//        //1.记录日志信息
//        Signature signature = pjp.getSignature();
//         className = pjp.getTarget().getClass().getSimpleName();
//         methodName = signature.getName();
//        log.info("className:{},methodName:{}",className,methodName);
//       startTime = System.currentTimeMillis();
//
//    }
//
//    @After("CmpTime()")
//    public void after() throws Throwable{
//        endTime = System.currentTimeMillis();
//        log.info("本次调用起始时间为 {},结束时间为 {},耗时 {}ms",DateUtil.conversionTime(startTime), DateUtil.conversionTime(endTime),endTime-startTime);
//
//    }
//
//}
