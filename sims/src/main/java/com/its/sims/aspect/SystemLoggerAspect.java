package com.its.sims.aspect;

import com.its.sims.annotation.SystemLogger;
import com.its.sims.model.Student;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.chain.contexts.ServletActionContext;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;

/**
 * Created by csz on 2017/9/12.
 */

@Aspect
@Component(value = "systemLoggerAspect")
public class SystemLoggerAspect {


    //controller层日志切点
    @Pointcut(value = "@annotation(com.its.sims.annotation.SystemLogger)")
    public void controllerAspect(){}

    //controller被切入方法执行前执行
    @Before("controllerAspect()")
    public void beforeControllerMethod(JoinPoint joinPoint){

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

       /* HttpSession session = request.getSession();
        Student sessionUser = (Student) session.getAttribute("sessionUser");



        //获取用户id
        sessionUser.getId();

        //获取用户名称
        sessionUser.getName();*/

        //获取用户操作IP
        getRemoteIpAddress(request);
        System.out.println("获取用户操作IP:"+getRemoteIpAddress(request));

        //得到操作的方法名
        joinPoint.getSignature().getName();
        joinPoint.getStaticPart().toString();

        try {
            getControllerMethodDescription(joinPoint);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        //获取操作的url
        request.getSession().getServletContext().getRealPath("");//得到工程目录,参数可具体到包名
        request.getRequestURL();//得到IE栏地址
        request.getRequestURI();//相对路径


    }



    //获取用户ip地址
    public String getRemoteIpAddress(HttpServletRequest request) {

        String ip = request.getHeader("X-Forwarded-For");
        if(StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = ip.indexOf(",");
            if(index != -1){
                return ip.substring(0,index);
            }else{
                return ip;
            }
        }
        ip = request.getHeader("X-Real-IP");
        if(StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
            return ip;
        }

        return request.getRemoteAddr();
    }


    /**
     * 获取Controller层注解的描述信息
     * @param joinPoint 切入点信息
     * @return Controller层注解信息
     * @throws ClassNotFoundException 一般不抛异常
     */
    private String getControllerMethodDescription(JoinPoint joinPoint) throws ClassNotFoundException {

        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();

        String targetName = joinPoint.getTarget().getClass().getName();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();

        String description = "";
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();

                if (clazzs.length == arguments.length) {

                    SystemLogger annotation = method.getAnnotation(SystemLogger.class);

                    description = method.getAnnotation(SystemLogger.class).description();
                    break;
                }
            }
        }

        return description;
    }
}
