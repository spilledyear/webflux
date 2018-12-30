package com.zto.sxy.webflux.mongo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * @author spilledyear
 * @date 2018/12/29 17:50
 */
@RunWith(SpringRunner.class)
public class MongoTest {
    private final WebTestClient client = WebTestClient.bindToServer().baseUrl("http://localhost:8080").build();


    @Test
    public void getAllMessagesShouldBeOk() {
        client.get().uri("/mongo/user").exchange().expectStatus().isOk();
    }
}
