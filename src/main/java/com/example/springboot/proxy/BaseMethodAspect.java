package com.example.springboot.proxy;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public abstract class BaseMethodAspect implements ApplicationContextAware {
    private ApplicationContext appContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        appContext = applicationContext;
    }

    protected abstract void pointcut();

    @Around("pointcut()")
    public Object advice(ProceedingJoinPoint point) {
        Class<? extends MethodAdviceHandler<?>> adviceHandlerType = getAdviceHandlerType();
        MethodAdviceHandler<?> adviceHandler = appContext.getBean(adviceHandlerType);
        return advice(point, adviceHandler);
    }

    private Object advice(ProceedingJoinPoint point, MethodAdviceHandler<?> adviceHandler) {
        boolean permitted = adviceHandler.onBefore(point);
        Object result;
        boolean thrown = false;
        long startTime = System.currentTimeMillis();
        if (permitted) {
            try {
                result = point.proceed();
            } catch (Throwable e) {
                thrown = true;
                adviceHandler.onThrow(point, e);
                result = adviceHandler.getOnThrow(point, e);
            }
        } else {
            result = adviceHandler.getOnForbid(point);
        }
        adviceHandler.onComplete(point, startTime, permitted, thrown, result);
        return result;
    }

    protected abstract Class<? extends MethodAdviceHandler<?>> getAdviceHandlerType();
}
