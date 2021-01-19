package com.example.springboot.retrofit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.TimeUnit;

/**
 * @author xingce
 * @date 2020/12/17 12:46
 */
public class FundClient {
    private OkHttpClient okHttpClient;

    private Retrofit retrofit;

    private FundClientProperties properties;

    public FundClient(FundClientProperties properties) {
        this.properties = properties;

        init();
    }

    private void init() {
        // 初始化okhttpclient
        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new ReqIdHeaderInterceptor())
                .connectTimeout(properties.getConnectTimeoutMillis(), TimeUnit.MILLISECONDS)
                .readTimeout(properties.getReadTimeoutMillis(), TimeUnit.MILLISECONDS)
                .writeTimeout(properties.getWriteTimeoutMillis(), TimeUnit.MILLISECONDS)
                .build();

        // 初始化Retrofit
        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(properties.getServiceUrlPrefix())
                .validateEagerly(false)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(new FundClientAdapterFactory(properties))
                .build();
    }

    /**
     * 创建Retrofit接口对象
     *
     * @param apiServiceClass apiServiceClass
     * @param <T> apiService
     * @return apiService
     */
    public <T> T createApi(final Class<T> apiServiceClass) {
        return this.retrofit.create(apiServiceClass);
    }
}
