package com.example.springboot.chain;

/**
 * @author xingce
 */
public abstract class AbstractBusinessService<Request, Response> implements
        BaseDutyService<Request>, BusinessService<Request, Response> {

    protected BusinessService<Request, Response> child;
    protected ServiceManager<BusinessService<Request, Response>> serviceManager;

    @Override
    public BusinessService<Request, Response> setChild(BusinessService<Request, Response> child) {
        this.child = child;
        return child;
    }

    public void setServiceManager(ServiceManager<BusinessService<Request, Response>> serviceManager) {
        this.serviceManager = serviceManager;
        this.serviceManager.addChild(this);
    }

    @Override
    public Response apply(Request request) {
        if (!isMyDuty(request)) {
            if (this.child != null) {
                return this.child.apply(request);
            } else {
                throw new RuntimeException("未找到具体实现类");
            }
        }
        return doApply(request);
    }

    protected abstract Response doApply(Request request);
}
