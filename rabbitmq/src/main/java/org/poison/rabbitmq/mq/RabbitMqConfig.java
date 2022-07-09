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
     * 延迟队列
     * 关键在于增加了一个参数"x-dead-letter-exchange"，意思是进入死信之后转入正常交换机“TEST_EXCHANGE”
     */
    @Bean
    public Queue testDelayQueue() {
        return QueueBuilder.durable(RabbitMqConstants.TEST_DELAY_QUEUE)
                .withArgument("x-dead-letter-exchange", RabbitMqConstants.TEST_EXCHANGE)
                .build();
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
     * 延迟交换机
     * 一定要用“TopicExchange”而非“DirectExchange”
     */
    @Bean
    public TopicExchange testDelayExchange() {
        return new TopicExchange(RabbitMqConstants.TEST_DELAY_EXCHANGE);
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
     * 绑定延迟队列和延迟交换机，使用一个错误的路由键，保证不会被消费
     */
    @Bean
    public Binding testDelayBinding() {
        return BindingBuilder.bind(testDelayQueue()).to(testDelayExchange()).with("#");
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
        //QUEUE
        rabbitAdmin.declareQueue(testQueue());
        rabbitAdmin.declareQueue(testDelayQueue());
        //EXCHANGE
        rabbitAdmin.declareExchange(testExchange());
        rabbitAdmin.declareExchange(testDelayExchange());
        //BINDING
        rabbitAdmin.declareBinding(testBinding());
        rabbitAdmin.declareBinding(testDelayBinding());
        return new RabbitAdmin(connectionFactory);
    }
}
