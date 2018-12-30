package com.zto.sxy.webflux.mongo;

import com.zto.sxy.webflux.dto.User;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author spilledyear
 * @date 2018/12/29 17:46
 */
@RestController()
@RequestMapping(value = "/mongo/user")
class MongoUserController {

    private final MongoUserRepository userRepository;

    public MongoUserController(MongoUserRepository posts) {
        this.userRepository = posts;
    }

    @GetMapping("")
    public Flux<User> all() {
        return this.userRepository.findAll();
    }

    @PostMapping("")
    public Mono<User> create(@RequestBody User user) {
        return this.userRepository.save(user);
    }

    @GetMapping("/{userId}")
    public Mono<User> get(@PathVariable("userId") String userId) {
        return this.userRepository.findById(userId);
    }

    @PutMapping("/{userId}")
    public Mono<User> update(@PathVariable("userId") String userId, @RequestBody User user) {
        return this.userRepository.findById(userId)
                .map(u -> {
                    u.setUserName(user.getUserName());
                    return u;
                })
                .flatMap(p -> this.userRepository.save(p));
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable("id") String id) {
        return this.userRepository.deleteById(id);
    }

}
