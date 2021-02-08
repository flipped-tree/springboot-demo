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

        // todo 幂等性校验

        log.info("msgId:{}", msgId);

        MessageProperties properties = message.getMessageProperties();
        long tag = properties.getDeliveryTag();

        boolean success = isSuccessSend();
        if (success) {
            /*
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
