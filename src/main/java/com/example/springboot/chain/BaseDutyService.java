package com.example.springboot.chain;

public interface BaseDutyService<Request> {

    boolean isMyDuty(Request request);

}
