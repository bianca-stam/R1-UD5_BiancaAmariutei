package org.goya.dam2.ud6;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

import java.io.IOException;

import org.springframework.context.annotation.Configuration;
import redis.embedded.RedisServer;

@Configuration
public class RedisConfig {

    private RedisServer redisServer;

    @PostConstruct
    public void startRedis() throws IOException {
        redisServer = new RedisServer(6379); // PUERTOO 6379!!
        redisServer.start();
    }

    @PreDestroy
    public void stopRedis() {
        if (redisServer != null) {
            redisServer.stop();
        }
    }
}

