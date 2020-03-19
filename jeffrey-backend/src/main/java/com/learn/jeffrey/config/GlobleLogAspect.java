package com.learn.jeffrey.config;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>&#x516c;&#x5171;&#x65e5;&#x5fd7;&#x62e6;&#x622a;</p>
 * @author lijianfei
 * @date 2019-12-10
 */
//声明是个切面
@Aspect
//声明是个spring管理的bean
@Component
@Slf4j
public class GlobleLogAspect {
	/**
	 * 可以通过切点表达式直接指定需要拦截的package,需要拦截的class 以及 method
	 * 切点表达式：execution()
	 * 还可以通过注解的方式
	 */
	@Pointcut("execution(public * com.learn.jeffrey..controller..*.*(..))")
	private void controllerLogPointCut() {
		
	}
	/**
	 * 以注解的方式拦截
	 */
	@Pointcut("@annotation(com.learn.jeffrey.config.Log)")
	private void logPointCut() {
		
	}
	/**
	 * @throws Throwable 
	 * @Around环绕通知，也可以使用 @Before (前置通知)  @After (后置通知)
	 * 
	 */
	@Around("controllerLogPointCut()")
	private Object aroundPointCut(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		//接口路径
		StringBuilder apiPath = new StringBuilder();
		Class<? extends Object> targetClass = proceedingJoinPoint.getTarget().getClass();
		String methodName = proceedingJoinPoint.getSignature().getName();
		if (targetClass.isAnnotationPresent(RequestMapping.class)) {
            RequestMapping requestMapping = (RequestMapping) targetClass.getAnnotation(RequestMapping.class);
            apiPath.append(Arrays.stream(requestMapping.value()).filter(StringUtils::isNotEmpty).findFirst().orElse(""));
        }
        Method[] methods = targetClass.getMethods();
        Optional<Method> targetMethod = Arrays.stream(methods).filter(method -> methodName.equals(method.getName())).findFirst();
        apiPath.append(targetMethod.isPresent() ? getMethodMapping(targetMethod.get()) : "");
        String path = apiPath.toString();
        if (log.isInfoEnabled()) {
            log.info("接口 " + path + " 位置: " + targetClass.getName() + "." + methodName + "()");
        }
        Object[] args = proceedingJoinPoint.getArgs();
        Object[] parseArgs = Arrays.stream(args).filter(arg -> !(arg instanceof HttpServletRequest)).toArray();
        if (log.isInfoEnabled()) {	
            log.info("接口 " + path + " 参数: " + JSON.toJSONString(parseArgs));
        }
        Object result = proceedingJoinPoint.proceed(args);
        String returnJson = JSON.toJSONString(result);
        if (log.isInfoEnabled()) {
            log.info("接口 " + path + " 访问结果: " + returnJson);
        }
        return result;
	}
	
	//获取方法路径名
    private String getMethodMapping(Method method) {
        if (method.isAnnotationPresent(PostMapping.class)) {
            PostMapping postMapping = method.getAnnotation(PostMapping.class);
            return Arrays.stream(postMapping.value()).filter(StringUtils::isNotEmpty).findFirst().orElse("");
        } else if (method.isAnnotationPresent(GetMapping.class)) {
            GetMapping getMapping = method.getAnnotation(GetMapping.class);
            return Arrays.stream(getMapping.value()).filter(StringUtils::isNotEmpty).findFirst().orElse("");
        }
        return "";
    }

    @Around("logPointCut()")
    public  Object logAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
    	System.out.println("-----log注解测试-----");
    	Object result = proceedingJoinPoint.proceed();
    	return result;
    }
}
