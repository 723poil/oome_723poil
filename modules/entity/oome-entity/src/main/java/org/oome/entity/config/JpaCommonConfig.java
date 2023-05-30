package org.oome.entity.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaAuditing
@EnableJpaRepositories(basePackages = "org.oome.entity")
@SpringBootConfiguration
public class JpaCommonConfig {

}