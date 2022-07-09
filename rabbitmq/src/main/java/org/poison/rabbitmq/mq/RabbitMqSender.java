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
    @SneakyThrows
    public void sendMessage(String exchange, String routingKey, Object obj) {
        String json = objectMapper.writeValueAsString(obj);
        log.info("RabbitMqSender sendMessage exchange :{} routingKey:{} message:{}", exchange, routingKey, json);
        try {
            //我们经常遇到的warning“Could not convert incoming message with content-type [text/plain], 'json' keyword missing. rabbitmq”就是因为没有设置contentType
            MessageProperties messageProperties = new MessageProperties();
            messageProperties.setContentType("application/json");
            Message message = new Message(json.getBytes(), messageProperties);
            amqpTemplate.convertAndSend(exchange, routingKey, message);
        } catch (Exception e) {
            log.error("RabbitMqSender sendMessage exchange :{} routingKey:{} message:{} error", exchange, routingKey, json, e);
        }
    }
}
