package com.douzone.mysite.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class MeasureExecutionTimeAspect {

	// *..* : mysite까지 잡음
	@Around("execution(* *..*.repository.*.*(..)) || execution(* *..*.service.*.*(..)) || execution(* *..*.controller.*.*(..))")
	public Object aroundAdvice(ProceedingJoinPoint pjp) throws Throwable{
		//before
		StopWatch sw = new StopWatch();
		sw.start();
		
		Object result = pjp.proceed();
		
		
		
		
		//after
		sw.stop();
		Long totalTime = sw.getTotalTimeMillis();
		
		String className = pjp.getTarget().getClass().getName(); // 어떤 클래스의 이름이 불렀는지
		String methodName = pjp.getSignature().getName(); // 어떤 메소드의 이름이 불렀는지
		String task = className + "." + methodName;
		System.out.println("[Execution Time][" + task + "] " + totalTime + "mills");
		
		return result;
		
	}
	
	
}
