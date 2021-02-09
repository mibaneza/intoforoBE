package com.ard333.springbootwebfluxjjwt.rest;

import com.ard333.springbootwebfluxjjwt.domain.CategoriesDomain;
import com.ard333.springbootwebfluxjjwt.model.CategoryModel;
import com.ard333.springbootwebfluxjjwt.service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CategoriesREST {
    @Autowired
    CategoriesService categoriesService;

    @GetMapping(value = "/api/web/categorias")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public Flux<CategoriesDomain> readAll(){
        return categoriesService.findAllCategories();
    }
    @GetMapping(value = "/api/web/categoriaslist")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public Mono<List<CategoryModel>> readAlsl(){
        return categoriesService.findAllCategorieslist();
    }

    @PostMapping(value = "/resource/categoria")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Mono<CategoriesDomain> createCategory(@RequestBody @Valid CategoriesDomain categoriesDomain){
        return categoriesService.saveCategories(categoriesDomain);
    }

    @PutMapping(value = "/resource/categoria/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Mono<CategoriesDomain> updateCategory(@PathVariable("id") String id,
                                                  @RequestBody @Valid CategoriesDomain categoriesDomain){
        return categoriesService.updateCategories(id,categoriesDomain);
    }

   @DeleteMapping(value = "/resource/categori/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public Mono<Void> deleteCategory(@PathVariable("id") String id){
        return categoriesService.deleteCategaries(id);
    }

}
