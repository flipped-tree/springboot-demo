package com.example.springboot.retrofit;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;

import java.io.IOException;

/**
 * @author xingce
 * @date 2020/12/17 12:46
 */
public class ReqIdHeaderInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        String reqId = MDC.get("req.id");
        Request origin = chain.request();
        if (StringUtils.isNotBlank(reqId)) {
            // add request header reqId from MDC
            Request request = origin.newBuilder().addHeader("req.id", reqId).build();
            return chain.proceed(request);
        }

        return chain.proceed(origin);
    }
}
