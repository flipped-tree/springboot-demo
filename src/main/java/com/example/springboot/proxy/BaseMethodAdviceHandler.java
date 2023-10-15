package com.example.springboot.proxy;

import cn.hutool.json.JSONUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;

public abstract class BaseMethodAdviceHandler<R> implements MethodAdviceHandler<R> {
    @Override
    public void onThrow(ProceedingJoinPoint point, Throwable e) {
        String methodDesc = getMethodDesc(point);
        Object[] args = point.getArgs();
        System.out.println("{" + methodDesc + "} 执行时出错，入参={" + JSONUtil.toJsonStr(args) + "}");
    }

    private String getMethodDesc(ProceedingJoinPoint point) {
        Object target = point.getTarget();
        String simpleName = target.getClass().getSimpleName();

        Signature signature = point.getSignature();
        String methodName = signature.getName();
        return simpleName + "." + methodName;
    }
}
