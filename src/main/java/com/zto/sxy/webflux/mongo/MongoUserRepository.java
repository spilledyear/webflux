package com.zto.sxy.webflux.mongo;

import com.zto.sxy.webflux.dto.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

/**
 * @author spilledyear
 * @date 2018/12/29 17:42
 */
interface MongoUserRepository extends ReactiveMongoRepository<User, String> {
}
