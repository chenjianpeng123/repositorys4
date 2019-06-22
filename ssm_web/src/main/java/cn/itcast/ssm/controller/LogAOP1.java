package cn.itcast.ssm.controller;

import cn.itcast.ssm.domain.SysLog;
import cn.itcast.ssm.service.SysLogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Date;


@Component
@Aspect
public class LogAOP1 {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private SysLogService sysLogService;

    @Around("execution(* cn.itcast.ssm.service.impl.*.*(..))")
    public Object recordLog(ProceedingJoinPoint pjp) throws Throwable {
        //收集日志参数
        SysLog sysLog = new SysLog();

        //获取访问时间
        long visitTime = System.currentTimeMillis();
        sysLog.setVisitTime(new Date(visitTime));
        //获取操作的用户
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = user.getUsername();
        sysLog.setUsername(username);
        //获取访问ip
        String ip = request.getRemoteAddr();
        //获取url
        String uri = request.getRequestURI();
        sysLog.setUrl(uri);
        //获取执行时长
        Object result = pjp.proceed();
        long endTime = System.currentTimeMillis();
        sysLog.setExecutionTime(endTime-visitTime);
       //访问类名+方法名
        String className = pjp.getTarget().getClass().getName();
        String mothodName = pjp.getSignature().getName();
        sysLog.setMethod("[类名 ]"+className+"[方法名] "+mothodName);
        //保存日志
        sysLogService.save(sysLog);
        return result;
    }

    public static void main(String[] args) {

       Class clazz = LogAOP1.class;
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            Annotation[] annotations = method.getDeclaredAnnotations();
            for (Annotation annotation : annotations) {
                Annotation annotation1 = method.getAnnotation(annotation.annotationType());
                if(annotation1.annotationType().equals(After.class)){
                    After after= (After) annotation1;
                    System.out.println(after.value());
                }else if(annotation1.annotationType().equals(Before.class)){
                    Before before = (Before) annotation1;
                    System.out.println(before.value());
                }
            }
        }
    }

}
