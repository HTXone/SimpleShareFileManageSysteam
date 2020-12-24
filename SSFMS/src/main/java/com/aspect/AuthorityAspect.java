package com.aspect;


import com.Service.UserSessionService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Aspect
public class AuthorityAspect {

    @Autowired
    private UserSessionService userSessionService;

    @Value("403.html")//错误页
    private String errorPage;


    @Around("execution(String com.Control.UserControl.*User*(..)) || execution(String com.Control.HistroryControl.*History*(..)) " +
            "|| execution(String com.Control.FileControl.*File*(..)) || execution(String com.Control.DirControl.*Dir*(..)) ")
    public Object userAuth(ProceedingJoinPoint joinPoint){
        try {
            return joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        try {
            response.sendRedirect(errorPage);//要改为错误页面
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
