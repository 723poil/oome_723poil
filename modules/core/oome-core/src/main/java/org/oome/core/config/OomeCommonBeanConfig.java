package org.oome.core.config;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootConfiguration
public class OomeCommonBeanConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        // 엄격한 바인딩 규칙을 따른다.
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        log.debug("ModelMapper Bean Created");
        return modelMapper;
    }
}
