package com.example.springboot.retrofit;

import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * @author xingce
 * @date 2020/12/17 12:09
 */
public interface RetrofitInterface {

    @POST("/fund/bankCardBin/query")
    CommonResponse<RetrofitResponse> testRetrofit(@Body RetrofitRequest request);

}
