package com.example.springboot.consumer;

import com.example.springboot.config.RabbitConfig;
import com.example.springboot.pojo.Mail;
import com.example.springboot.util.MessageHelper;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author xingce
 * @date 2020/12/19 11:30
 */
@Component
@Slf4j
public class Consumer {

    @RabbitListener(queues = RabbitConfig.MAIL_QUEUE_NAME)
    public void consume(Message message, Channel channel) throws IOException {
        Mail mail = MessageHelper.msgToObj(message, Mail.class);
        log.info("收到消息: {}", mail.getMsgId());

        String msgId = mail.getMsgId();

//        MsgLog msgLog = msgLogService.selectByMsgId(msgId);
//        if (null == msgLog || msgLog.getStatus().equals(Constant.MsgLogStatus.CONSUMED_SUCCESS)) {// 消费幂等性
//            log.info("重复消费, msgId: {}", msgId);
//            return;
//        }

        log.info("msgId:{}", msgId);

        MessageProperties properties = message.getMessageProperties();
        long tag = properties.getDeliveryTag();

        boolean success = isSuccessSend();
        if (success) {
            /*
             * msgLogService.updateStatus(msgId, Constant.MsgLogStatus.CONSUMED_SUCCESS);
             * 消费确认
             */
            channel.basicAck(tag, false);
            log.info("消费ack");
        } else {
            channel.basicNack(tag, false, true);
            log.info("消费nack");
        }
    }

    private boolean isSuccessSend() {
        return true;
    }

}
