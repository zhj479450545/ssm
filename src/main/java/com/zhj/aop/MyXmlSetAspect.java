package com.zhj.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * 切面类:通过xml配置切面
 * Created by Administrator on 2017/2/10.
 */
public class MyXmlSetAspect {
    public void doAfter(JoinPoint joinPoint){
        System.out.println("log Ending method: " + joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName());
    }
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long time = System.currentTimeMillis();
        Object retunValue = proceedingJoinPoint.proceed();
        time = System.currentTimeMillis() - time;
        System.out.println("procced time:" + time +"ms");
        return retunValue;
    }

    public void doBefore(JoinPoint joinPoint) {
        System.out.println("log Before method: " + joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName());
    }
    public void doThrowing(JoinPoint joinPoint, Throwable throwable) {
        System.out.println("method " + joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "throw exception");
        System.out.println(throwable.getMessage());
    }
}
