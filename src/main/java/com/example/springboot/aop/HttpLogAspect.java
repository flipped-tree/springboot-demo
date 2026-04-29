package com.example.springboot.aop;

import com.google.gson.Gson;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;

@Aspect
@Component
public class HttpLogAspect {
    private static final Logger log = LoggerFactory.getLogger(HttpLogAspect.class);
    private static final Gson GSON = new Gson();

    @Around("@annotation(com.example.springboot.aop.HttpLog)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        HttpServletRequest request = getCurrentRequest();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();

        Map<String, Object> requestLog = new LinkedHashMap<>();
        requestLog.put("type", "http_request");
        requestLog.put("uri", request == null ? null : request.getRequestURI());
        requestLog.put("method", request == null ? null : request.getMethod());
        requestLog.put("clientIp", getClientIp(request));
        requestLog.put("className", signature.getDeclaringTypeName());
        requestLog.put("methodName", signature.getName());
        requestLog.put("args", filterArgs(joinPoint.getArgs()));
        log.info(GSON.toJson(requestLog));

        Object result;
        try {
            result = joinPoint.proceed();
        } catch (Throwable ex) {
            Map<String, Object> errorLog = new LinkedHashMap<>();
            errorLog.put("type", "http_response");
            errorLog.put("uri", request == null ? null : request.getRequestURI());
            errorLog.put("method", request == null ? null : request.getMethod());
            errorLog.put("costMs", System.currentTimeMillis() - startTime);
            errorLog.put("success", false);
            errorLog.put("errorMessage", ex.getMessage());
            log.error(GSON.toJson(errorLog), ex);
            throw ex;
        }

        Map<String, Object> responseLog = new LinkedHashMap<>();
        responseLog.put("type", "http_response");
        responseLog.put("uri", request == null ? null : request.getRequestURI());
        responseLog.put("method", request == null ? null : request.getMethod());
        responseLog.put("costMs", System.currentTimeMillis() - startTime);
        responseLog.put("success", true);
        responseLog.put("result", result);
        log.info(GSON.toJson(responseLog));
        return result;
    }

    private HttpServletRequest getCurrentRequest() {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        if (!(attributes instanceof ServletRequestAttributes)) {
            return null;
        }
        return ((ServletRequestAttributes) attributes).getRequest();
    }

    private Object[] filterArgs(Object[] args) {
        if (args == null || args.length == 0) {
            return new Object[0];
        }
        Object[] filteredArgs = new Object[args.length];
        for (int i = 0; i < args.length; i++) {
            Object arg = args[i];
            if (arg instanceof ServletRequest || arg instanceof ServletResponse) {
                filteredArgs[i] = arg.getClass().getSimpleName();
            } else {
                filteredArgs[i] = arg;
            }
        }
        return filteredArgs;
    }

    private String getClientIp(HttpServletRequest request) {
        if (request == null) {
            return null;
        }
        String forwarded = request.getHeader("X-Forwarded-For");
        if (forwarded != null && !forwarded.trim().isEmpty()) {
            return forwarded.split(",")[0].trim();
        }
        return request.getRemoteAddr();
    }
}
