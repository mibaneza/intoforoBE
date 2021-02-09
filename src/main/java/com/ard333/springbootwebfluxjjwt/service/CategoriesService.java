package com.ard333.springbootwebfluxjjwt.service;

import com.ard333.springbootwebfluxjjwt.domain.CategoriesDomain;
import com.ard333.springbootwebfluxjjwt.domain.PostDomain;
import com.ard333.springbootwebfluxjjwt.model.CategoryModel;
import com.ard333.springbootwebfluxjjwt.model.Duall;
import com.ard333.springbootwebfluxjjwt.repository.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public Flux<CategoriesDomain> findAllCategories(){
        return categoriesRepository.findAll();
    }
    public Mono<List<CategoryModel>> findAllCategorieslist() throws NullPointerException{
        List<CategoriesDomain> categoriesDomains = categoriesRepository.findAll().collectList().block();
        List<CategoryModel> categoryModels = new ArrayList<>();
        for (CategoriesDomain cate : categoriesDomains){
            PostDomain postDomain = postService.findId(cate.getIdpost()).block();

            categoryModels.add(new CategoryModel(
                    cate,
                    postDomain,
                    userService.findid(postDomain.getIduser()).block(),
                    updatePostService.findByIdTitle(postDomain.getIdupdate()),
                    commentsService.countByPost(postDomain.getIdpost())
            ));
        }
                    return Mono.just(categoryModels);
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
    public Mono<CategoriesDomain> updateCategoriePost(Duall duall){
        return categoriesRepository.findById(duall.getDualv1())
                .map(c -> {
                    c.setIdpost(duall.getDualv2());
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
