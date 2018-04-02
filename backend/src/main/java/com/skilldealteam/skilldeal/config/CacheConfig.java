package com.skilldealteam.skilldeal.config;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.skilldealteam.skilldeal.persistence.model.tables.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheConfig {

    @Bean
    public LoadingCache<String, User> userCache() {
        final CacheLoader<String, User> loader;
        loader = new CacheLoader<String, User>() {
            @Override
            public User load(String key) {
                return null;
            }
        };
        final LoadingCache<String, User> userCache;
        userCache = CacheBuilder.newBuilder().build(loader);
        return userCache;
    }
}
