package org.poison.rabbitmq.controller;

import lombok.extern.slf4j.Slf4j;
import org.poison.rabbitmq.mq.RabbitMqConstants;
import org.poison.rabbitmq.mq.RabbitMqSender;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("test")
public class TestController {

    @Resource
    private RabbitMqSender rabbitMqSender;

    @PostMapping(value = "")
    public String test(@RequestBody Body abc) {
        rabbitMqSender.sendDelayMessage(RabbitMqConstants.TEST_DELAY_EXCHANGE, RabbitMqConstants.TEST_ROUTING_KEY, abc, 5000L);
        return "helloWorld";
    }

    @RabbitListener(queues = RabbitMqConstants.TEST_QUEUE, containerFactory = "testContainer")
    public void testListener(Message message) {
        String msgJson = new String(message.getBody());
        log.info("RabbitListener receiveMessage message: {}", msgJson);
    }
}
