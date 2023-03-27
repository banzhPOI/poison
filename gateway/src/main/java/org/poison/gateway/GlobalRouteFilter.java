//package org.poison.gateway;
//
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.cloud.gateway.filter.GatewayFilterChain;
//import org.springframework.cloud.gateway.filter.GlobalFilter;
//import org.springframework.http.server.reactive.ServerHttpRequest;
//import org.springframework.http.server.reactive.ServerHttpResponse;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Mono;
//
//@Component
//@Slf4j
//public class GlobalRouteFilter implements GlobalFilter {
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        ServerHttpRequest request = exchange.getRequest();
//        // 打印请求路径
//        String path = request.getPath().pathWithinApplication().value();
//        String requestMethod = request.getMethod().name();
//
//        //网关转发到其他服务器的需要移除统一加的前缀
//        ServerHttpRequest newRequest = request.mutate().contextPath(StringUtils.EMPTY).build();
//        ServerWebExchange newExChange = exchange.mutate().request(newRequest).build();
//        return chain.filter(newExChange).then(
//                Mono.fromRunnable(() -> {
//                    // 参数
//                    ServerHttpResponse response = exchange.getResponse();
//                    int value = response.getStatusCode().value();
//                    log.info("[Gateway] <=== {} {}: {}", value, requestMethod, path);
//                })
//        );
//    }
//
//
//}
