package com.zp.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * Description: im
 * Created by Zhu Peng on 2019/5/22
 */
@Configuration
public class AppBean {

    private final RedisConnectionFactory mRedisConnectionFactory;

    @Autowired
    public AppBean(RedisConnectionFactory redisConnectionFactory) {
        this.mRedisConnectionFactory = redisConnectionFactory;
    }


    @Bean("redis")
    public TokenStore redisTokenStore() {
        return new RedisTokenStore(mRedisConnectionFactory);
    }
}
