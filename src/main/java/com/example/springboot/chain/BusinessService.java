package com.example.springboot.chain;

public interface BusinessService<Request, Response> extends BaseService<BusinessService<Request, Response>> {

    Response apply(Request request);
}
