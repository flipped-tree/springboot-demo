package com.example.springboot.retrofit;

import lombok.Data;

/**
 * RetrofitProperties
 *
 * @author Liu Chang
 * @date 2019/10/11
 */
@Data
public class FundClientProperties {

    /** 服务的Host */
    private String serviceUrlPrefix;

    /** 客户端名称 */
    private String clientName;

    /** OkHttp默认值 */
    private Integer connectTimeoutMillis = 10000;

    /** OkHttp默认值 */
    private Integer readTimeoutMillis = 10000;

    /** OkHttp默认值 */
    private Integer writeTimeoutMillis = 10000;

}
