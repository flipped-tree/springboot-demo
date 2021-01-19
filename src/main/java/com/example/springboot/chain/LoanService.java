package com.example.springboot.chain;

import com.example.springboot.vo.RealRequest;
import com.example.springboot.vo.RealResponse;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @author xingce
 */
public abstract class LoanService extends AbstractBusinessService<RealRequest, RealResponse> {

    @Resource
    private ServiceManager<BusinessService<RealRequest, RealResponse>> loanServiceManager;

    @PostConstruct
    public void init() {
        this.setServiceManager(loanServiceManager);
    }

    @Override
    protected RealResponse doApply(RealRequest realRequest) {
        return invoke(realRequest);
    }

    protected abstract RealResponse invoke(RealRequest realRequest);
}
