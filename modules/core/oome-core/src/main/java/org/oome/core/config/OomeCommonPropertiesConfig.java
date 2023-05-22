package org.oome.core.config;

import lombok.extern.slf4j.Slf4j;
import org.oome.core.properties.CommonUrlProperties;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootConfiguration
public class OomeCommonPropertiesConfig {

    @Bean
    @ConfigurationProperties(prefix = "org.oome.api.context-path")
    @ConditionalOnProperty(prefix = "org.oome.api.context-path", name = "is-use", havingValue = "true",
    matchIfMissing = true)
    public CommonUrlProperties commonUrlProperties() {
        log.debug("CommonUrlProperties Bean Created");
        return new CommonUrlProperties();
    }
}
