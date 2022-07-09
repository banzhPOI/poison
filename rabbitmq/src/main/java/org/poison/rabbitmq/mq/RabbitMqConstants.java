package org.poison.rabbitmq.mq;

public class RabbitMqConstants {

    /**
     * QUEUE
     */
    public static final String TEST_QUEUE = "test_queue";

    public static final String TEST_DELAY_QUEUE = "test_delay_queue";

    /**
     * EXCHANGE
     */
    public static final String TEST_EXCHANGE = "test_exchange";

    public static final String TEST_DELAY_EXCHANGE = "test_delay_exchange";

    /**
     * ROUTING_KEY
     */
    public static final String TEST_ROUTING_KEY = "test_routing_key";

    /**
     * 由于延迟队列不需要真的被消费，所以不需要路由键，我们用一个“#”来代替
     */
    public static final String TEST_DELAY_ROUTING_KEY = "#";

}
