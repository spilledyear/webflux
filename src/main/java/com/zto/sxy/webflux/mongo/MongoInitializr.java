package com.zto.sxy.webflux.mongo;

import com.zto.sxy.webflux.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

/**
 * @author spilledyear
 * @date 2018/12/29 17:33
 */
@Component
@Slf4j
class MongoInitializr implements CommandLineRunner {

    private final MongoUserRepository userRepository;

    public MongoInitializr(MongoUserRepository repository) {
        this.userRepository = repository;
    }

    @Override
    public void run(String[] args) {
        log.info("start mongo initialization");
        this.userRepository
                .deleteAll()
                .thenMany(Flux.just("站三", "李四")
                        .flatMap(name -> this.userRepository.save(User.builder().userName(name).build()))
                )
                .log()
                .subscribe(
                        null,
                        null,
                        () -> log.info("done mongo initialization")
                );
    }
}
