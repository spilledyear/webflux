package com.zto.sxy.webflux.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.redis.core.RedisHash;

import java.time.LocalDateTime;

/**
 * @author spilledyear
 * @date 2018/12/29 18:58
 */
// 如果mongo和redis公用一个dto，这个注解会报错
//@RedisHash("user")
@Document("user")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    private String userId;

    private String userName;

    @CreatedDate
    private LocalDateTime createTime;
}
