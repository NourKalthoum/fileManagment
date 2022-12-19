package com.example.demo;

import com.example.demo.entity.*;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectOriented {

    Logger logger = LoggerFactory.getLogger(AspectOriented.class);

    // show all files
    @AfterReturning("execution(* com.example.demo.controller.FileController.showAll(..))")
    public void showAll(JoinPoint joinPoint) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        logger.info("\n UserName: " + currentPrincipalName + "-> Show All files");
    }

    // show all groups
    @AfterReturning("execution(* com.example.demo.controller.GroupController.showGroups(..))")
    public void showGroups(JoinPoint joinPoint) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        logger.info("\n UserName: " + currentPrincipalName + "-> Show All groups");
    }

    // delete group
    @AfterReturning("execution(* com.example.demo.controller.GroupController.deleteGroup(..))")
    public void deleteGroup(JoinPoint joinPoint) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        logger.info("\n UserName: " + currentPrincipalName + "-> Deleted The group: (" + (joinPoint.getArgs()[0]) + ")");
    }
    // delete file
    @AfterReturning("execution(* com.example.demo.controller.FileController.deleteFile(..))")
    public void deleteFile(JoinPoint joinPoint) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        logger.info("\n UserName: " + currentPrincipalName + "-> Deleted The file: (" + (joinPoint.getArgs()[0]) + ")");
    }

   

}