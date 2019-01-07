package com.zto.sxy.webflux.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author spilledyear
 * @date 2019-01-07 11:17
 */
@RestController
public class MockController {

    @Autowired
    private MockMetaService mockMetaService;


    @GetMapping("/mock/{serviceId}")
    public MockMeta query(@PathVariable Long serviceId) {
        return mockMetaService.query(serviceId);
    }
}
