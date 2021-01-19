package com.example.springboot.service.impl;

import com.example.springboot.chain.LoanService;
import com.example.springboot.vo.RealRequest;
import com.example.springboot.vo.RealResponse;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class NewLoanService extends LoanService {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Override
    protected RealResponse invoke(RealRequest realRequest) {
        rabbitTemplate.convertAndSend("","");
        System.out.println("new loan service");
        return null;
    }

    @Override
    public boolean isMyDuty(RealRequest realRequest) {
        return "new".equals(realRequest.getFundCode());
    }
}
