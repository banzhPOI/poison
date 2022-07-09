package org.poison.rabbitmq.mq;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Component
public class RabbitMqSender {

    @Resource
    private ObjectMapper objectMapper;

    @Resource
    private AmqpTemplate amqpTemplate;

    /**
     * 发送广播消息
     */
    public void sendMessage(String exchange, String routingKey, Object obj) {
        Message message = convertMessage(exchange, routingKey, obj);
        try {
            amqpTemplate.convertAndSend(exchange, routingKey, message);
        } catch (Exception e) {
            log.error("RabbitMqSender sendMessage exchange :{} routingKey:{} error", exchange, routingKey, e);
        }
    }

    /**
     * 发送延迟消息
     *
     * @param exchange   延迟交换机
     * @param routingKey 消费队列的路由键！不是延迟队列的！
     * @param obj
     * @param expireTime 过期时间(毫秒)
     */
    public void sendDelayMessage(String exchange, String routingKey, Object obj, Long expireTime) {
        Message message = convertMessage(exchange, routingKey, obj);
        try {
            amqpTemplate.convertAndSend(exchange, routingKey, message, postMsg -> {
                postMsg.getMessageProperties().setExpiration(expireTime.toString());
                return postMsg;
            });
        } catch (Exception e) {
            log.error("RabbitMqSender sendMessage exchange :{} routingKey:{} error", exchange, routingKey, e);
        }
    }

    @SneakyThrows
    private Message convertMessage(String exchange, String routingKey, Object obj) {
        String json = objectMapper.writeValueAsString(obj);
        log.info("RabbitMqSender sendMessage exchange :{} routingKey:{} message:{}", exchange, routingKey, json);
        //我们经常遇到的warning“Could not convert incoming message with content-type [text/plain], 'json' keyword missing. rabbitmq”就是因为没有设置contentType
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType("application/json");
        Message message = new Message(json.getBytes(), messageProperties);
        return message;
    }
}
