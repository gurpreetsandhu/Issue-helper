package com.stackroute.faq.logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Logger {
    private org.slf4j.Logger logger = LoggerFactory.getLogger(Logger.class);

    @Before(value = "execution(* com.stackroute.faq.controller.FaqController .*(..))")
    public void beforeAdvice(JoinPoint joinPoint) {
        logger.info("Entering method : " + joinPoint.getSignature().getName());
    }

    @After(value = "execution(* com.stackroute.faq.controller.FaqController .*(..))")
    public void afterAdvice(JoinPoint joinPoint) {
        logger.info("Exited Method : " + joinPoint.getSignature().getName());
    }


    @AfterReturning(value = "execution(* com.stackroute.faq.controller.FaqController .*(..))", returning = "responseEntity")
    public void afterAdviceReturned(JoinPoint joinPoint, ResponseEntity responseEntity) {
        logger.info("After method : " + joinPoint.getSignature().getName());
        logger.info("Object with following data will be persisted " + responseEntity);
    }

    @AfterThrowing(value = "execution(* com.stackroute.faq.controller.FaqController .*(..))", throwing = "exception")
    public void afterThrowingAdvice(JoinPoint joinPoint, Exception exception) {
        logger.error("After Error in Method : " + joinPoint.getSignature().getName());
        logger.error("Exception thrown" + exception.getMessage());
    }
}
