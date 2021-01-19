package com.example.springboot.pojo;

/**
 * @author xingce
 * @date 2020/12/19 11:31
 */
public class Mail {

    private String msgId;

    private String message;

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
