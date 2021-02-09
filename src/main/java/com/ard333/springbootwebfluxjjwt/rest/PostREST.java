package com.ard333.springbootwebfluxjjwt.rest;

import com.ard333.springbootwebfluxjjwt.domain.PostDomain;
import com.ard333.springbootwebfluxjjwt.repository.UpdatePostRepository;
import com.ard333.springbootwebfluxjjwt.service.CategoriesService;
import com.ard333.springbootwebfluxjjwt.service.PostService;
import com.ard333.springbootwebfluxjjwt.service.util.Getdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.security.Principal;

@RestController
public class PostREST {
    @Autowired
    Getdate getdate;

    @Autowired
    CategoriesService categoriesService;

    @Autowired
    UpdatePostRepository updatePostRepository;

    @Autowired
    PostService postService;

    @GetMapping(value = "/api/web/posts")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public Flux<PostDomain> readAsll(){
        return postService.findAllPosts();
    }
    @GetMapping(value = "/api/web/posts/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public Flux<PostDomain> readAsllCate(@PathVariable("id") String id){
        return postService.findAllPostCategories(id);
    }

    @PostMapping(value = "/resource/us/post/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Mono<PostDomain> createUserPost(@PathVariable("id") String id, Principal principal,
                                                 @RequestBody  PostDomain postDomain){
        return postService.saveUserPost(id,postDomain,principal);
    }
    @PostMapping(value = "/resource/ad/post/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Mono<PostDomain> createAdminPost(@PathVariable("id") String id, Principal principal,
                                       @RequestBody  PostDomain postDomain){
        return postService.saveAdminPost(id,postDomain,principal);
    }

    @PutMapping(value = "/resource/us/post/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Mono<PostDomain> updatePost(@PathVariable("id") String id,
                                              @RequestBody PostDomain postDomain, Principal principal){
        return postService.updatePostUser(id,postDomain,principal);

    }

    @PutMapping(value = "/resource/ad/post/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Mono<?> updatePostAdmin(@PathVariable("id") String id,
                              @RequestBody @Valid PostDomain postDomain, Principal principal){
        return postService.updatePostAdmin(id,postDomain,principal);

    }

    @DeleteMapping(value = "/resource/us/post/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public Mono<Void> deletePost(@PathVariable("id") String id, Principal principal){
        return postService.deletePostAdminUser(id,principal);
    }
    @DeleteMapping(value = "/resource/ad/post/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public Mono<Void> deletePostAdmin(@PathVariable("id") String id){
        return postService.deletePostAdmin(id);
    }

    /*      List<UpdateDomain> updateDomains = null ;
        updateDomains.add(new UpdateDomain(
                "INICIADO",
                "1213146",
                "13212341546",
                getdate.date(),
                getdate.date())

        );*/
}