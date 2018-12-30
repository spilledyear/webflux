package com.zto.sxy.webflux.redis;

import com.zto.sxy.webflux.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;

/**
 * @author spilledyear
 * @date 2018/12/29 19:02
 */
@Component
public class RedisUserHandler {

    @Autowired
    private RedisUserRepository userRepository;


    public Mono<ServerResponse> all(ServerRequest req) {
        return ServerResponse.ok().body(this.userRepository.findAll(), User.class);
    }

    public Mono<ServerResponse> create(ServerRequest req) {
        return req.bodyToMono(User.class)
                .flatMap(post -> this.userRepository.save(post))
                .flatMap(p -> ServerResponse.created(URI.create("/redis/posts/" + p.getUserId())).build());
    }

    public Mono<ServerResponse> get(ServerRequest req) {
        return this.userRepository.findById(req.pathVariable("userId"))
                .flatMap(post -> ServerResponse.ok().body(Mono.just(post), User.class))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> update(ServerRequest req) {
        return Mono.zip(
                (data) -> {
                    User u1 = (User) data[0];
                    User u2 = (User) data[1];
                    u1.setUserName(u2.getUserName());
                    return u1;
                },
                this.userRepository.findById(req.pathVariable("userId")),
                req.bodyToMono(User.class)
        ).cast(User.class)
                .flatMap(post -> this.userRepository.save(post))
                .flatMap(post -> ServerResponse.noContent().build());
    }

    public Mono<ServerResponse> delete(ServerRequest req) {
        return ServerResponse.noContent().build(this.userRepository.deleteById(req.pathVariable("userId")));
    }

}

