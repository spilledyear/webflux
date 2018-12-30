package com.zto.sxy.webflux.redis;

import com.zto.sxy.webflux.dto.User;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * @author spilledyear
 * @date 2018/12/29 18:58
 */
@Component
public class RedisUserRepository {
    private final static String HASH_KEY = "user";

    ReactiveRedisOperations<String, User> template;

    public RedisUserRepository(ReactiveRedisOperations<String, User> template) {
        this.template = template;
    }

    Flux<User> findAll() {
        return template.<String, User>opsForHash().values(HASH_KEY);
    }

    Mono<User> findById(String id) {
        return template.<String, User>opsForHash().get(HASH_KEY, id);
    }

    Mono<User> save(User user) {
        if (user.getUserId() != null) {
            user.setUserId(UUID.randomUUID().toString());
        }
        return template.<String, User>opsForHash().put(HASH_KEY, user.getUserId(), user)
                .log()
                .map(p -> user);

    }

    Mono<Void> deleteById(String id) {
        return template.<String, User>opsForHash().remove(HASH_KEY, id)
                .flatMap(p -> Mono.<Void>empty());
    }

    Mono<Boolean> deleteAll() {
        return template.<String, User>opsForHash().delete(HASH_KEY);
    }

}
