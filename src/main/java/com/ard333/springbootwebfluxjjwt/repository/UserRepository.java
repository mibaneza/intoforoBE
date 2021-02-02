package com.ard333.springbootwebfluxjjwt.repository;

import com.ard333.springbootwebfluxjjwt.domain.UserDomain;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveMongoRepository<UserDomain, String> {
    Mono<UserDomain> findByUsername(String username);
    Mono<Boolean> existsByUsername(String username);
    Flux<UserDomain> findByRoles(String username);

}
