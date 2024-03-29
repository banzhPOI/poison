package org.poison.gateway;

import cn.dev33.satoken.stp.StpUtil;
import org.poison.common.constant.GlobalConstant;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class DefaultPreGlobalFilter implements GlobalFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest newRequest = exchange
                .getRequest();
        // 排除掉登录的url
        if (!GlobalConstant.LOGIN_URL.contains(newRequest.getURI().getPath())
                && !GlobalConstant.REGISTER_URL.contains(newRequest.getURI().getPath())) {
            try {
                // 为请求追加 CURRENT_USER 参数
                newRequest.mutate()
                        .header(GlobalConstant.CURRENT_USER, StpUtil.getLoginIdAsString())
                        .build();
            } catch (Exception ignored) {
            }
        }
        ServerWebExchange newExchange = exchange.mutate().request(newRequest).build();
        return chain.filter(newExchange);
    }

}
