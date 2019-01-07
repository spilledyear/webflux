package com.zto.sxy.webflux.cache;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author spilledyear
 * @date 2019-01-07 11:13
 */
@Service
public class MockMetaServiceImpl implements MockMetaService {

    @Cacheable(value = "mockMeta", key = "#serviceId")
    @Override
    public MockMeta query(Long serviceId) {
        return new MockMeta(serviceId, "mockMeta");
    }
}
