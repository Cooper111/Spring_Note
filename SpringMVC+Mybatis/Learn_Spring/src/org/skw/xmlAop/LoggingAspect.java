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
			//ǰ��֪ͨ
			System.out.println("Start");
			//ִ��Ŀ�귽��
			result = proceedingJoinPoint.proceed();
			//����֪ͨ
			System.out.println("return: " + result);
		} catch (Throwable throwable) {
			//�쳣֪ͨ
			System.out.println("Exception: ");
			throwable.printStackTrace();
		}
		//����֪ͨ
		System.out.println("end");
		return result;
	}
}
