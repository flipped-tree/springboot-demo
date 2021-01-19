package com.example.springboot.chain;

import com.example.springboot.vo.RealRequest;
import com.example.springboot.vo.RealResponse;
import org.springframework.stereotype.Service;

@Service
public class LoanServiceManager extends AbstractBusinessServiceManager<RealRequest, RealResponse> {
    @Override
    public RealResponse apply(RealRequest realRequest) {
        return this.child.apply(realRequest);
    }
}
