package org.oome;

import lombok.extern.slf4j.Slf4j;
import org.oome.api.utils.OomeStringUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class OomeWebLocalApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(OomeWebLocalApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.debug(OomeStringUtils.convertToCamelCase("hello_world"));
    }
}
