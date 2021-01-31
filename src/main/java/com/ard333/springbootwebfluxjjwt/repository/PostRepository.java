package com.ard333.springbootwebfluxjjwt.repository;

import com.ard333.springbootwebfluxjjwt.domain.PostDomain;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface PostRepository extends ReactiveMongoRepository<PostDomain, String> {
    Mono<PostDomain> findByIdpostAndIduser(String idpost,String iduser);
    Mono<PostDomain> findByIdpostAndEst(String idpost,Boolean est);


}
