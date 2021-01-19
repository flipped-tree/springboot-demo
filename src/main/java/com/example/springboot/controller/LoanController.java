package com.example.springboot.controller;

import com.example.springboot.annotation.Validation;
import com.example.springboot.config.RabbitConfig;
import com.example.springboot.pojo.Mail;
import com.example.springboot.util.MessageHelper;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author xingce
 * @date 2020/12/18 21:57
 */
@RestController
public class LoanController {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @PostMapping("/fund/loan")
    @Validation
    public void loan(@RequestBody String message) {
        String msgId = UUID.randomUUID().toString();

        Mail mail = new Mail();
        mail.setMsgId(msgId);
        mail.setMessage(message);

        // todo 插入一条发送消息 msgId
        CorrelationData correlationData = new CorrelationData(msgId);
        // 发送消息
        rabbitTemplate.convertAndSend(RabbitConfig.MAIL_EXCHANGE_NAME, RabbitConfig.MAIL_ROUTING_KEY_NAME, MessageHelper.objToMsg(mail), correlationData);
    }

}
