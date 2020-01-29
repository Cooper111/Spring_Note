package org.skw.controller;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class MyUser {
	@Before(value = "execution(* org.skw.controller.User.*(..))")
	public void before() {
		System.out.println("before¡­¡­");
	}
	
	@After(value = "execution(* org.skw.controller.User.*(..))")
	public void after() {
		System.out.println("after¡­¡­");
	}
}
