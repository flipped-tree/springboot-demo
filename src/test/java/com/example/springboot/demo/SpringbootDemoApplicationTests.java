package com.example.springboot.demo;

import com.example.springboot.chain.BusinessService;
import com.example.springboot.vo.RealRequest;
import com.example.springboot.vo.RealResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class SpringbootDemoApplicationTests {

    @Resource
    BusinessService<RealRequest, RealResponse> loanServiceManager;

    @Test
    void contextLoads() {
    }

    @Test
    public void testChain() {
        RealRequest realRequest = new RealRequest();
        realRequest.setFundCode("new");
        loanServiceManager.apply(realRequest);
    }

}
