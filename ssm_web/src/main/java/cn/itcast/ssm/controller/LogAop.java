package cn.itcast.ssm.controller;

import cn.itcast.ssm.domain.SysLog;
import cn.itcast.ssm.service.SysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect
public class LogAop {
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private SysLogService sysLogService;

    private Date visitTime;//访问时间
    private Class executionClass;//访问的类
    private Method executionMethod;//访问的方法


    //前置通知
    @Before("execution(* cn.itcast.ssm.controller.*.*(..))")
    public void doBefore(JoinPoint jp) throws Exception {
        visitTime = new Date();//获取访问时间
        executionClass = jp.getTarget().getClass();//获取访问的类
        String methodName = jp.getSignature().getName();//获取访问方法的名称
        Object[] args = jp.getArgs();//获取访问方法的参数
        if (args == null || args.length == 0) {
            executionMethod = executionClass.getMethod(methodName);//只能获取无参数的方法
        } else {
            //有参数 将arges中的所有元素遍历 获取的对应的Class 将其放置的Class[]中
            Class[] classArgs = new Class[args.length];
            for (int i = 0; i < classArgs.length; i++) {
                classArgs[i] = args[i].getClass();
            }
            //获取有参数的方法
            executionMethod = executionClass.getMethod(methodName, classArgs);
        }
    }

    //后置通知
    @After("execution(* cn.itcast.ssm.controller.*.*(..))")
    public void doAfter(JoinPoint jp) throws Exception {
        //判断  获取url
        if (executionClass != null && executionMethod != null && executionClass != LogAop.class) {
            //获取类上的RequestMapping对象
            RequestMapping classAnnotation = (RequestMapping) executionClass.getAnnotation(RequestMapping.class);
            if (classAnnotation != null) {
                String[] classValue = classAnnotation.value();
                //获取方法上的RequestMapping对象
                RequestMapping methodAnnotation = executionMethod.getAnnotation(RequestMapping.class);
                if (methodAnnotation != null) {
                    String[] methodValue = methodAnnotation.value();
                    String url = classValue[0] + methodValue[0];
                    //创建sysLog对象 将日志相关的的信息封装到sysLog对象中
                    SysLog sysLog = new SysLog();
                    //获取访问的时长
                    Long executionTime = System.currentTimeMillis() - visitTime.getTime();
                    sysLog.setExecutionTime(executionTime);
                    sysLog.setUrl(url);
                    //获取ip
                    String ip = request.getRemoteAddr();
                    sysLog.setIp(ip);
                    //获取当前操作的用户
                    SecurityContext context = SecurityContextHolder.getContext();//从上下文字获取当前登陆的用户
                    User user = (User) context.getAuthentication().getPrincipal();
                    String username = user.getUsername();
                    sysLog.setUsername(username);
                    sysLog.setMethod("[类名] " + executionClass.getName() + "[方法名 ]" + executionMethod.getName());
                    sysLog.setVisitTime(visitTime);

                    sysLogService.save(sysLog);
                }
            }
        }

    }

}
