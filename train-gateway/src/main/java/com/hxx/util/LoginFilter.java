package com.hxx.util;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class LoginFilter implements Ordered, GlobalFilter {
    /**
     * 自定义网关过滤器，用于权限验证。
     * 在请求通过网关路由到具体服务之前，此过滤器会检查请求是否包含有效的token。
     * 指定路径的请求将绕过token验证。
     *
     * @param exchange 服务器web交换对象，包含请求和响应信息。
     * @param chain 过滤器链，用于继续或停止过滤器链的执行。
     * @return Mono<Void> 表示异步操作的结果。
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 获取请求路径
        String path = exchange.getRequest().getURI().getPath();

        // 指定路径的请求不需要token验证
        // 放行
        if (path.contains("/admin") ||
                path.contains("/trainMember/login") ||
                path.contains("/trainMember/sendCode") ){
            return chain.filter(exchange);
        }

        // 从请求头中获取token
        String token = exchange.getRequest().getHeaders().getFirst("token");

        // 如果token不存在或为空，则返回未授权状态
        if (token==null || token.isEmpty()) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        // 验证token的有效性
        if (JwtUtils.verify(token)){
            return chain.filter(exchange);
        }else {
            // 如果token无效，则返回未授权状态
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
    }


    /**
     * 获取当前对象的顺序。
     *
     * 本方法重写了父类中的getOrder方法，返回值为0，表示当前对象的执行顺序。
     * 在使用注解处理器时，顺序通常很重要，因为它决定了处理程序的执行序列。
     * 返回0意味着这个处理程序应该在所有其他处理程序之前执行。
     *
     * @return 返回0，表示当前对象的执行顺序。
     */
    @Override
    public int getOrder() {
        return 0;
    }

}
