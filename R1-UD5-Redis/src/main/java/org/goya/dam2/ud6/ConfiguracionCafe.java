package org.goya.dam2.ud6;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class ConfiguracionCafe {
  @Bean
  ReactiveRedisOperations<String, Cafe> redisOperations(ReactiveRedisConnectionFactory factory) {
    Jackson2JsonRedisSerializer<Cafe> serializer = new Jackson2JsonRedisSerializer<>(Cafe.class);

    RedisSerializationContext.RedisSerializationContextBuilder<String, Cafe> builder =
        RedisSerializationContext.newSerializationContext(new StringRedisSerializer());

    RedisSerializationContext<String, Cafe> context = builder.value(serializer).build();

    return new ReactiveRedisTemplate<>(factory, context);
  }

}


