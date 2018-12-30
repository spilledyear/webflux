package com.zto.sxy.webflux.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * @author spilledyear
 * @date 2018/12/30 16:59
 */
@Configuration
@Component
public class RedisUserRouter {
    @Bean
    public RouterFunction<ServerResponse> routes(RedisUserHandler redisUserHandler) {
        return route(GET("/redis/user"), redisUserHandler::all)
                .andRoute(POST("/redis/user"), redisUserHandler::create)
                .andRoute(GET("/redis/user/{userId}"), redisUserHandler::get)
                .andRoute(PUT("/redis/user/{userId}"), redisUserHandler::update)
                .andRoute(DELETE("/redis/user/{userId}"), redisUserHandler::delete);
    }
}
