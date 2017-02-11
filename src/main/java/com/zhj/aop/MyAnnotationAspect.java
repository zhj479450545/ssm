package com.zhj.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * 切面类：通过注解配置切面
 * Created by Administrator on 2017/2/10.
 */
@Aspect
public class MyAnnotationAspect {
    @Pointcut("execution(* com.zhj.service.LoginLogService.*(*))")
    private void pointCutMethod() {

    }

    //声明前置通知
    @Before("pointCutMethod()")
    public void doBefore(JoinPoint joinPoint) {
        System.out.println("log before method: " + joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName());
    }

    //声明最终通知
    @After("pointCutMethod()")
    public void doAfter(JoinPoint joinPoint) {
        System.out.println("log after method:" + joinPoint.getTarget().getClass().getName() + "," + joinPoint.getSignature().getName());
    }

    //声明后置通知
    //    @AfterReturning(pointcut = "pointCutMethod()", returning = "result") 若切入的方法有返回值，才能使用 @AfterReturning
    public void doAfterReturning(JoinPoint joinPoint, String result) { // result参数，是切入方法的返回值
        System.out.println("log afterReturning method:" + joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName());
    }

    //声明异常通知
    @AfterThrowing(pointcut = "pointCutMethod()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Exception e) {
        System.out.println("log afterReturning method:" + joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName());
        System.out.println(e.getMessage());
    }

//    @Around("pointCutMethod()") 环绕通知是前置通知和后置通知的组合，故若切入方法没有返回值的话也不能是哟环绕通知
    public void doAroungd(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("进入方法--环绕通知");
        proceedingJoinPoint.proceed();
        System.out.println("退出方法--环绕通知");
    }

}
