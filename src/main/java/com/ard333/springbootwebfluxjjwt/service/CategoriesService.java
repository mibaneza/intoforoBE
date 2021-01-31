package com.ard333.springbootwebfluxjjwt.service;

import com.ard333.springbootwebfluxjjwt.domain.CategoriesDomain;
import com.ard333.springbootwebfluxjjwt.repository.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@Service
public class CategoriesService {

    @Autowired
    CategoriesRepository categoriesRepository;

    public Flux<CategoriesDomain> findAllCategories(){
        return categoriesRepository.findAll();
    }

    public Mono<CategoriesDomain> saveCategories( CategoriesDomain categoriesDomain){
        return categoriesRepository.save(categoriesDomain);
    }

    public Mono<CategoriesDomain> findId(String id){
        return categoriesRepository.findById(id);
    }

    public Mono<CategoriesDomain> updateCategories(String id ,CategoriesDomain categoriesDomain){
        return categoriesRepository.findById(id)
                .map(c -> categoriesDomain)
                .flatMap(categoriesRepository::save);
    }

    public Mono<Void> deleteCategaries(String id){
        return  categoriesRepository.findById(id)
                .switchIfEmpty(Mono.error(Exception::new))
                .flatMap(categoriesRepository::delete);
    }


}
