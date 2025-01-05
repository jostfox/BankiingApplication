package org.example.service;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.joda.time.LocalDateTime;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Arrays;

import static org.example.BankingApp.LOGFILE;

@Component
@Aspect
public class AopLogger {


    @Around(value = "execution(* org.example.controller..*.*(..))")
    public Object controllerLogger(ProceedingJoinPoint joinPoint) throws Throwable {
        String initialValue = "\nClient calls method " + joinPoint +
                " with arguments: " + Arrays.toString(joinPoint.getArgs());
        System.out.println(initialValue);
        Object value = joinPoint.proceed();
        System.out.println("\nMethod replies: " + value);
        loggingToFile(initialValue + "\nMethod replies: " + value);
        return value;
    }

    private void loggingToFile(Object value) {
        try
                (FileWriter fileWriter = new FileWriter(LOGFILE, true)) {
            String lineSeparator = "\n----------" + LocalDateTime.now() + "-------------";
            fileWriter.write(lineSeparator + value + System.lineSeparator());
        } catch (
                IOException exception) {
            System.out.println("Error writing to log file");
        }
    }
}
