package org.example.service;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class AopLogger {

    @Around(value = "execution(* org.example.controller..*.*(..))")
    public Object controllerLogger(ProceedingJoinPoint joinPoint) throws Throwable{
        System.out.println("\nClient calls method " + joinPoint +
                " with arguments: " + Arrays.toString(joinPoint.getArgs()));
        Object value = joinPoint.proceed();
        System.out.println("\nMethod replies: " + value);
        return value;
    }

}
