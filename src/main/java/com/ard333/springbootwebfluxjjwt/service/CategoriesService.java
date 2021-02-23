package com.ard333.springbootwebfluxjjwt.service;

import com.ard333.springbootwebfluxjjwt.domain.CategoriesDomain;
import com.ard333.springbootwebfluxjjwt.domain.PostDomain;
import com.ard333.springbootwebfluxjjwt.model.CategoryModel;
import com.ard333.springbootwebfluxjjwt.model.Duall;
import com.ard333.springbootwebfluxjjwt.repository.CategoriesRepository;
import com.ard333.springbootwebfluxjjwt.rest.response.MensajeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoriesService {
    @Autowired
    private UpdatePostService updatePostService;
    @Autowired
    CommentsService commentsService;
    @Autowired
    UserService userService;
    @Autowired
    PostService postService;
    @Autowired
    CategoriesRepository categoriesRepository;
    private static final String SUCCES = "Succes";
    private static final String OK = "OK";
    public Flux<CategoriesDomain> findAllCategories(){
        return categoriesRepository.findAll();
    }
    public Mono<CategoriesDomain> findOneCategory(String linktitle){
        return  categoriesRepository.findByLinktitle(linktitle);
    }


    public Mono<List<CategoryModel>> findAllCategorieslist() throws NullPointerException{
        List<CategoriesDomain> categoriesDomains = categoriesRepository.findAll().collectList().block();
        List<CategoryModel> categoryModels = new ArrayList<>();
        for (CategoriesDomain cate : categoriesDomains){
            if(cate.getIdpost().equals("0")){
                categoryModels.add(new CategoryModel(
                        cate,
                        null,
                        (long) 0)
                );
            }else{
            PostDomain postDomain = postService.findId(cate.getIdpost()).block();
            categoryModels.add(new CategoryModel(
                    cate,
                    postDomain,
                    postService.countByPost(cate.getIdcategories())
            ));
            }
        }
                    return Mono.just(categoryModels);
    }/*
    public Mono<List<CategoryModel>> findAllCawdategorieslist() throws NullPointerException{
        return categoriesRepository
                .findAll()
                .collectList()
                .map((list) -> {
                    List<CategoryModel> categoryModels = new ArrayList<>();
                    for (CategoriesDomain cate : list){
                        PostDomain postDomain = postService.findId(cate.getIdpost()).block();
                        categoryModels.add(new CategoryModel(
                                cate,
                                postDomain,
                                commentsService.countByPost(postDomain.getIdpost())));
                    }
                    return categoryModels;
                });

    }*/
    public Mono<ResponseEntity<Mono<CategoriesDomain>>> registerCategories(CategoriesDomain categoriesDomain){
        return categoriesRepository.findByLinktitle(categoriesDomain.getLinktitle())
                .map((cate) -> new ResponseEntity<>( Mono.just(cate), HttpStatus.CONFLICT))
                .defaultIfEmpty(new ResponseEntity<>(saveCategories(categoriesDomain),HttpStatus.CREATED ))
                ;
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
    public Mono<CategoriesDomain> updateCategoriePost(String idcategory, String idpost){
        return categoriesRepository.findById(idcategory)
                .map(c -> {
                    c.setIdpost(idpost);
                   return c;
                })
                .flatMap(categoriesRepository::save);
    }

    public Mono<Void> deleteCategaries(String id){
        return  categoriesRepository.findById(id)
                .switchIfEmpty(Mono.error(Exception::new))
                .flatMap(categoriesRepository::delete);
    }


}
