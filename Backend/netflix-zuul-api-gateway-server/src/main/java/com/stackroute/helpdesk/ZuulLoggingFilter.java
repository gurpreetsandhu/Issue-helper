package com.stackroute.helpdesk;


import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
 * Implement zuul logging filter by extending zuul filter
 */
@Component
public class ZuulLoggingFilter{

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Before(value = "execution(* com.stackroute.helpdesk .*(..))")
	public void beforeAdvice(JoinPoint joinPoint) {
		logger.info("Entering method : " + joinPoint.getSignature().getName());
	}

	@After(value = "execution(* com.stackroute.helpdesk .*(..))")
	public void afterAdvice(JoinPoint joinPoint) {
		logger.info("Exited Method : " + joinPoint.getSignature().getName());
	}


	@AfterReturning(value = "execution(* com.stackroute.helpdesk .*(..))", returning = "responseEntity")
	public void afterAdviceReturned(JoinPoint joinPoint, ResponseEntity responseEntity) {
		logger.info("After method : " + joinPoint.getSignature().getName());
		logger.info("Object with following data will be persisted " + responseEntity);
	}

	@AfterThrowing(value = "execution(* com.stackroute.helpdesk .*(..))", throwing = "exception")
	public void afterThrowingAdvice(JoinPoint joinPoint, Exception exception) {
		logger.error("After Error in Method : " + joinPoint.getSignature().getName());
		logger.error("Exception thrown" + exception.getMessage());
	}
}
