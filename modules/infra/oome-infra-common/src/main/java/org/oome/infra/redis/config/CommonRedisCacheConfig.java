package org.oome.infra.redis.config;

import org.oome.infra.redis.generator.CustomKeyGenerator;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;

@SpringBootConfiguration
public class CommonRedisCacheConfig {

    @Bean("customKeyGenerator")
    public KeyGenerator keyGenerator() {
        return new CustomKeyGenerator();
    }
}
