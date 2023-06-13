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
        basePackages = "org.oome.infra.redis.hash.server1",
        redisTemplateRef = "redisTemplateServer1",
        keyValueTemplateRef = "keyValueTemplateServer1"
)
@SpringBootConfiguration
public class RedisServer1CommonConfig {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private Integer port;

    @Bean("redisConnectionFactoryServer1")
    public RedisConnectionFactory redisConnectionFactoryServer1() {
        return new LettuceConnectionFactory(host, port);
    }

    @Bean("redisTemplateServer1")
    public RedisTemplate<?, ?> redisTemplateServer1(
            @Qualifier("redisConnectionFactoryServer1")
            RedisConnectionFactory redisConnectionFactoryServer1
    ) {
        RedisTemplate<byte[], byte[]> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactoryServer1);
        return redisTemplate;
    }

    @Bean("keyValueTemplateServer1")
    public KeyValueTemplate keyValueTemplateServer1(
            @Qualifier("redisConnectionFactoryServer1")
            RedisConnectionFactory redisConnectionFactoryServer1
    ) {
        return new RedisKeyValueTemplate(
                new RedisKeyValueAdapter(redisTemplateServer1(redisConnectionFactoryServer1)),
                new RedisMappingContext()
        );
    }

    @Bean("cacheManagerServer1")
    @Primary
    public CacheManager cacheManagerServer1() {
        RedisCacheManager.RedisCacheManagerBuilder builder = RedisCacheManager
                .RedisCacheManagerBuilder
                .fromConnectionFactory(redisConnectionFactoryServer1());

        return builder.build();
    }
}
