package org.oome.infra.redis.generator;

import org.jetbrains.annotations.NotNull;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.lang.NonNull;

import java.lang.reflect.Method;

public class CustomKeyGenerator implements KeyGenerator {

    @Override
    public @NotNull Object generate(@NonNull Object target, Method method, @NonNull Object... params) {

        return method.getName() +
                SimpleKeyGenerator.generateKey(params);
    }
}
