package org.oome.api.common.services;

import org.springframework.stereotype.Service;

@Service
public class HelloWorldService {

    public String returnHelloWorld() {
        return "hello_world";
    }
}
