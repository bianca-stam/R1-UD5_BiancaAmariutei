package org.goya.dam2.ud6;

import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class ControllerCafe {
  private final ReactiveRedisOperations<String, Cafe> coffeeOps;

  ControllerCafe(ReactiveRedisOperations<String, Cafe> coffeeOps) {
    this.coffeeOps = coffeeOps;
  }

  @GetMapping("/coffees")
  public Flux<Cafe> all() {
    return coffeeOps.keys("*")
        .flatMap(coffeeOps.opsForValue()::get);
  }
}


