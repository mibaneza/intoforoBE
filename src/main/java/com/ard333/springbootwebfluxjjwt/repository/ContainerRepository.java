package com.ard333.springbootwebfluxjjwt.repository;

import com.ard333.springbootwebfluxjjwt.domain.ContainerDomain;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface ContainerRepository extends ReactiveMongoRepository<ContainerDomain, String> {
    Mono<ContainerDomain> findByIdpostAndEst(String id, Boolean bol);
    Mono<ContainerDomain> findByLinktitle(String idpost);
    Mono<ContainerDomain> findByIdpost(String idpost);
}