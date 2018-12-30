package com.zto.sxy.webflux.redis;

import com.zto.sxy.webflux.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author spilledyear
 * @date 2018/12/29 19:00
 */
@Component
@Slf4j
class RedisInitializer implements CommandLineRunner {

    @Autowired
    private RedisUserRepository userRepository;

    @Override
    public void run(String[] args) {
        log.info("start redis initialization");
        this.userRepository
                .deleteAll()
                .thenMany(Flux.just("张三", "李四")
                        .flatMap(name -> this.userRepository.save(
                                User.builder().userId(UUID.randomUUID().toString())
                                        .userName(name)
                                        .createTime(null).build()
                                )
                        )
                )
                .log()
                .subscribe(
                        null,
                        null,
                        () -> log.info("done redis initialization")
                );
    }

}

