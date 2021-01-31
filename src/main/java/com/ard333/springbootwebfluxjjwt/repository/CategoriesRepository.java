package com.ard333.springbootwebfluxjjwt.repository;

import com.ard333.springbootwebfluxjjwt.domain.CategoriesDomain;
import com.ard333.springbootwebfluxjjwt.domain.UserDomain;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface CategoriesRepository extends ReactiveMongoRepository<CategoriesDomain, String> {
//    Mono<CategoriesDomain> findByIdpost(String idpost);
}
