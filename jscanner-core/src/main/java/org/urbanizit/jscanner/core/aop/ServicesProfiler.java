package org.urbanizit.jscanner.core.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class ServicesProfiler {
	
	private Logger logger = LoggerFactory.getLogger(ServicesProfiler.class);
	
    @Pointcut("execution(* org.urbanizit.jscanner.back.services.impl.*.*(..))")
    public void serviceCall() { }

    @Around("serviceCall()")
	public Object profileExecution(ProceedingJoinPoint pjp) throws Throwable {

		Object result = null;

		try{
			logger.debug("start : {}", pjp.getSignature());
			result = pjp.proceed();	
			logger.debug("success : {}", pjp.getSignature());
			
		}catch (Exception e) {
			logger.debug("error : {}", pjp.getSignature());
			throw e;		
		}
		return result;
	}
    
    
    
    @Around("serviceCall()")
	public Object profileTime(ProceedingJoinPoint pjp) throws Throwable {
		long start = System.currentTimeMillis();
		
		try{
			return pjp.proceed();			
		}finally{
			long elapsedTime = System.currentTimeMillis() - start;
			logger.debug("Execution : {} in {} ms", pjp.getSignature(), elapsedTime);
		}		
	}  
    
}