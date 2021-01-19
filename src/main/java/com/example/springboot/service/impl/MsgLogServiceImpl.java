package com.example.springboot.service.impl;

import com.example.springboot.pojo.MsgLog;
import com.example.springboot.service.MsgLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MsgLogServiceImpl implements MsgLogService {

    @Override
    public void updateStatus(String msgId, Integer status) {
        MsgLog msgLog = new MsgLog();
        msgLog.setMsgId(msgId);
        msgLog.setStatus(status);
        msgLog.setUpdateTime(new Date());
//        msgLogMapper.updateStatus(msgLog);
    }

    @Override
    public MsgLog selectByMsgId(String msgId) {
        return new MsgLog();
    }

    @Override
    public List<MsgLog> selectTimeoutMsg() {
        return new ArrayList<>();
    }

    @Override
    public void updateTryCount(String msgId, Date tryTime) {
    }

}
