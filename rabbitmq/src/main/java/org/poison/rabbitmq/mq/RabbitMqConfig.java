package org.poison.rabbitmq.mq;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
public class RabbitMqConfig {


    /**
     * QUEUE
     * 注册队列Bean
     */

    @Bean
    public Queue testQueue() {
        return new Queue(RabbitMqConstants.TEST_QUEUE);
    }

    /**
     * EXCHANGE
     * 注册交换机Bean
     */

    @Bean
    public DirectExchange testExchange() {
        return new DirectExchange(RabbitMqConstants.TEST_EXCHANGE);
    }

    /**
     * BINDING
     * 交换机的队列使用路由键绑定
     */

    @Bean
    public Binding testBinding() {
        return BindingBuilder.bind(testQueue()).to(testExchange()).with(RabbitMqConstants.TEST_ROUTING_KEY);
    }

    /**
     * CONTAINER
     * Listener用的
     */

    @Resource
    private CachingConnectionFactory connectionFactory;

    @Resource
    private SimpleRabbitListenerContainerFactoryConfigurer factoryConfigure;

    private final static int CONCURRENT_CONSUMERS = 30;

    private final static int MAX_CONCURRENT_CONSUMERS = 60;

    @Bean(name = "testContainer")
    SimpleRabbitListenerContainerFactory testContainer() {
        return createSimpleRabbitListenerContainerFactory(CONCURRENT_CONSUMERS, MAX_CONCURRENT_CONSUMERS);
    }

    private SimpleRabbitListenerContainerFactory createSimpleRabbitListenerContainerFactory(int concurrentConsumers, int maxConcurrentConsumers) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factoryConfigure.configure(factory, connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        factory.setAcknowledgeMode(AcknowledgeMode.AUTO);
        factory.setConcurrentConsumers(concurrentConsumers);
        factory.setMaxConcurrentConsumers(maxConcurrentConsumers);
        factory.setPrefetchCount(30);
        return factory;
    }

    /**
     * RABBIT_ADMIN
     * 能自动注册队列、交换机、绑定关系的关键：RabbitAdmin
     */
    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        rabbitAdmin.declareQueue(testQueue());
        rabbitAdmin.declareExchange(testExchange());
        rabbitAdmin.declareBinding(testBinding());
        return new RabbitAdmin(connectionFactory);
    }
}
