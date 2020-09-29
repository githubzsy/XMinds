package com.zhoushiya.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhoushiya
 * @date 2020/9/29 17:35
 */
@Configuration
public class GatewayConfig {
    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder routeLocatorBuilder){
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();

        // 现在访问 localhost:9527/guonei 将会转发到 https://news.baidu.com/guonei
        return routes.route("path_route_baidu",r->r.path("/guonei").uri("https://news.baidu.com/guonei")).build();
    }
}
