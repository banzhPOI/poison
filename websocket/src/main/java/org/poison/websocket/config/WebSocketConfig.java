package org.poison.websocket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker  //用于启用我们的WebSocket服务器下的子协议stomp来传输基于代理（MessageBroker）的消息，这时控制器支持使用@MessageMapping
//https://juejin.cn/post/7041181976635637790
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //添加一个endpointSang端点，客户端通过这个断点进行连接，允许使用socketJs方式访问，允许跨域
        //在网页上我们就可以通过这个链接
        //http://localhost:8080/endpointSang
        //来和服务器的WebSocket连接
        registry.addEndpoint("/endpointSang")
                //.addInterceptors(newHttpSessionHandshakeInterceptor())
                .setAllowedOrigins("*")//允许跨域设置
                .withSockJS();//用来为不支持websocket的浏览器启用备选项，使用了SockJS
    }


    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        //启动一个简单基于内存的消息代理,接受消息用户必须以这个开头的路径才能收到消息
        config.enableSimpleBroker("/user", "/topic", "/queue");
        //全局使用的消息前缀（客户端主动发送消息前缀），以/app开头的数据会被@MessageMapping拦截进入方法体
        config.setApplicationDestinationPrefixes("/app");
        //点对点使用的订阅前缀（客户端订阅路径上会体现出来），不设置的话，默认也是/user/
        config.setUserDestinationPrefix("/user");
    }
}
