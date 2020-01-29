package org.skw.xmlAop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

import java.util.List;


public class LoggingAspect {
	public void beforeMethod(JoinPoint joinpoint) {
		String methodName = joinpoint.getClass().getName();
		List<Object> args = Arrays.asList(joinpoint.getArgs());
		System.out.println("The method "+ methodName + " begin with:" + args);
	}
	
	public void afterMethod(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		System.out.println("The method " + methodName + " end");
	}
	
	public void afterReturning(JoinPoint joinPoint, Object result) {
		String methodName = joinPoint.getSignature().getName();
		System.out.println("The method " + methodName + " end with" + result);
	}
	
	public void afterThrowing(JoinPoint joinPoint, Exception e) {
		String methodName = joinPoint.getSignature().getName();
		System.out.println("The method " + methodName + " occurs exception" + e);
	}
	
	public Object around(ProceedingJoinPoint proceedingJoinPoint) {
		Object result = null;
		try {
			//前置通知
			System.out.println("Start");
			//执行目标方法
			result = proceedingJoinPoint.proceed();
			//返回通知
			System.out.println("return: " + result);
		} catch (Throwable throwable) {
			//异常通知
			System.out.println("Exception: ");
			throwable.printStackTrace();
		}
		//后置通知
		System.out.println("end");
		return result;
	}
}
