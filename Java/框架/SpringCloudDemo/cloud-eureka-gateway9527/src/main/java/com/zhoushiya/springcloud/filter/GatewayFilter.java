package com.zhoushiya.springcloud.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author zhoushiya
 * @date 2020/9/29 19:59
 */
@Component
public class GatewayFilter implements GlobalFilter, Ordered {
    /**
     *
     * @param exchange 封装了request和response
     * @param chain 调用链
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        HttpCookie sessionId = exchange.getRequest().getCookies().getFirst("sessionId");
        // 如果没有cookie
        if (sessionId == null) {
            // 设置未授权
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            // 退出
            return exchange.getResponse().setComplete();
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
