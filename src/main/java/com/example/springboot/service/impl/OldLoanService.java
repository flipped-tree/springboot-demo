package com.example.springboot.service.impl;

import com.example.springboot.chain.LoanService;
import com.example.springboot.vo.RealRequest;
import com.example.springboot.vo.RealResponse;
import org.springframework.stereotype.Service;

@Service
public class OldLoanService extends LoanService {
    @Override
    protected RealResponse invoke(RealRequest realRequest) {
        System.out.println("old loan service");
        return null;
    }

    @Override
    public boolean isMyDuty(RealRequest realRequest) {
        return "".equals(realRequest.getFundCode());
    }
}
