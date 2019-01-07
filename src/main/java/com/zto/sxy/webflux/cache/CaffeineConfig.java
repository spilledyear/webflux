package com.zto.sxy.webflux.cache;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.CaffeineSpec;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author spilledyear
 * @date 2019-01-07 12:58
 */
@Configuration
public class CaffeineConfig {
    @Value("${spring.cache.caffeine.spec}")
    private String caffeineSpec;


    @Primary
    @Bean(name = "caffeineCacheManager")
    public CacheManager caffeineCacheManager() {
        Caffeine caffeine = Caffeine.from(caffeineSpec);
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        cacheManager.setCaffeine(caffeine);
        return cacheManager;
    }
}
