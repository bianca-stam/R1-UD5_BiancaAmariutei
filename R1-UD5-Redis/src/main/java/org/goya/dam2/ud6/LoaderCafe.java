package org.goya.dam2.ud6;

import org.springframework.context.annotation.DependsOn;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import jakarta.annotation.PostConstruct;
import java.util.UUID;

@Component
@DependsOn("redisConfig") // IMPORTANTE!! Esto obliga a que RedisConfig se ejecute primero
public class LoaderCafe {
  private final ReactiveRedisConnectionFactory factory;
  private final ReactiveRedisOperations<String, Cafe> coffeeOps;

  public LoaderCafe(ReactiveRedisConnectionFactory factory, ReactiveRedisOperations<String, Cafe> coffeeOps) {
    this.factory = factory;
    this.coffeeOps = coffeeOps;
  }

  @PostConstruct
  public void loadData() {
    factory.getReactiveConnection().serverCommands().flushAll().thenMany(
        Flux.just("Jet Black Redis", "Darth Redis", "Black Alert Redis")
            .map(name -> new Cafe(UUID.randomUUID().toString(), name))
            .flatMap(coffee -> coffeeOps.opsForValue().set(coffee.id(), coffee)))
        .thenMany(coffeeOps.keys("*")
            .flatMap(coffeeOps.opsForValue()::get))
        .subscribe(System.out::println);
  }
}


