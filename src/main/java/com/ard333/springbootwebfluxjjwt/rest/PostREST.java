package com.ard333.springbootwebfluxjjwt.rest;

import com.ard333.springbootwebfluxjjwt.domain.PostDomain;
import com.ard333.springbootwebfluxjjwt.domain.UpdateDomain;
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

    @PostMapping(value = "/resource/post/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Mono<UpdateDomain> createPost(@PathVariable("id") String id, Principal principal,
                                                 @RequestBody @Valid PostDomain postDomain){
        return postService.savePost(id,postDomain,principal).map((m)->{
            String[] arrSplit = principal.getName().split(",");
            return new UpdateDomain(
                    "INICIADO",
                    arrSplit[0],
                    arrSplit[1],
                    m.getIdpost(),
                    getdate.date(),
                    getdate.date());
        }).flatMap(updatePostRepository::save);
    }

    @PutMapping(value = "/resource/post/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Mono<?> updatePost(@PathVariable("id") String id,
                                              @RequestBody @Valid PostDomain postDomain, Principal principal){
        return postService.updatePostUser(id,postDomain,principal);

    }

    @PutMapping(value = "/resource/ad/post/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Mono<?> updatePostAdmin(@PathVariable("id") String id,
                              @RequestBody @Valid PostDomain postDomain, Principal principal){
        return postService.updatePostAdmin(id,postDomain,principal);

    }

    @DeleteMapping(value = "/resource/post/{id}")
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