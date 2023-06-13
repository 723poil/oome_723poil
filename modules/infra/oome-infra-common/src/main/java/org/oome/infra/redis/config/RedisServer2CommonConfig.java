package org.oome.infra.redis.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.data.keyvalue.core.KeyValueTemplate;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisKeyValueAdapter;
import org.springframework.data.redis.core.RedisKeyValueTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.mapping.RedisMappingContext;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@EnableRedisRepositories(
        basePackages = "org.oome.infra.redis.hash.server2",
        redisTemplateRef = "redisTemplateServer2",
        keyValueTemplateRef = "keyValueTemplateServer2"
)
@SpringBootConfiguration
public class RedisServer2CommonConfig {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private Integer port;

    @Bean("redisConnectionFactoryServer2")
    public RedisConnectionFactory redisConnectionFactoryServer2() {
        return new LettuceConnectionFactory(host, port);
    }

    @Bean("redisTemplateServer2")
    public RedisTemplate<?, ?> redisTemplateServer2(
            @Qualifier("redisConnectionFactoryServer2")
            RedisConnectionFactory redisConnectionFactoryServer2
    ) {
        RedisTemplate<byte[], byte[]> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactoryServer2);
        return redisTemplate;
    }

    @Bean("keyValueTemplateServer2")
    public KeyValueTemplate keyValueTemplateServer2(
            @Qualifier("redisConnectionFactoryServer2")
            RedisConnectionFactory redisConnectionFactoryServer2
    ) {
        return new RedisKeyValueTemplate(
                new RedisKeyValueAdapter(redisTemplateServer2(redisConnectionFactoryServer2)),
                new RedisMappingContext()
        );
    }

    @Bean("cacheManagerServer2")
    @Primary
    public CacheManager cacheManagerServer2() {
        RedisCacheManager.RedisCacheManagerBuilder builder = RedisCacheManager
                .RedisCacheManagerBuilder
                .fromConnectionFactory(redisConnectionFactoryServer2());

        return builder.build();
    }
}
