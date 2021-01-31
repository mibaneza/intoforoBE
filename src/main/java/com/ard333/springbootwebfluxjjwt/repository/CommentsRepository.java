package com.ard333.springbootwebfluxjjwt.repository;

import com.ard333.springbootwebfluxjjwt.domain.CommentsDomain;
import com.ard333.springbootwebfluxjjwt.domain.PostDomain;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface CommentsRepository extends ReactiveMongoRepository<CommentsDomain, String> {
    Mono<CommentsDomain> findByNickuserAndComentpostAndIdcoment(String iduser , String idpost, String idcomment);
    Flux<CommentsDomain> findByComentpostOrderByIndat(String idpost);
}
