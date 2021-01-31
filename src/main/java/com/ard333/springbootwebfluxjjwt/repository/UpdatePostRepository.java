package com.ard333.springbootwebfluxjjwt.repository;

import com.ard333.springbootwebfluxjjwt.domain.UpdateDomain;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UpdatePostRepository extends ReactiveMongoRepository<UpdateDomain, String> {
    Mono<UpdateDomain> findByIdcolleAndUsername(String idColle, String iduser);
    Mono<UpdateDomain> findByIdcolleAndUsernameAndTitle(String idColle, String iduser,String title);
}
