package com.ard333.springbootwebfluxjjwt.rest;

import com.ard333.springbootwebfluxjjwt.domain.ContainerDomain;
import com.ard333.springbootwebfluxjjwt.domain.PostDomain;
import com.ard333.springbootwebfluxjjwt.repository.UpdatePostRepository;
import com.ard333.springbootwebfluxjjwt.service.CategoriesService;
import com.ard333.springbootwebfluxjjwt.service.ContainerService;
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

    @Autowired
    ContainerService containerService;

    @GetMapping(value = "/api/web/posts")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public Flux<PostDomain> readAsll(){
        return postService.findAllPosts();
    }

    @GetMapping(value = "/api/web/posts/{idcategory}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public Flux<PostDomain> readAsllCate(@PathVariable("idcategory") String idcategory){
        return postService.findAllPostCategories(idcategory);
    }

    @GetMapping(value = "/api/web/post/{linktiltle}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public Mono<ContainerDomain> readPostConatiner(@PathVariable("linktiltle") String linktiltle){
        return containerService.findByLinkTitle(linktiltle);
    }

    @PostMapping(value = "/resource/user/post/{linktitleCategory}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Mono<PostDomain> createUserPost(@PathVariable("linktitleCategory") String linktitleCategory, Principal principal,
                                                @RequestBody ContainerDomain containerDomain){
        return postService.saveUserPost(linktitleCategory,containerDomain,principal.getName());
    }

    @PostMapping(value = "/resource/admin/post/{linktitleCategory}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Mono<PostDomain> createAdminPost(@PathVariable("linktitleCategory") String linktitleCategory, Principal principal,
                                            @RequestBody  ContainerDomain containerDomain){
        return postService.saveAdminPost(linktitleCategory,containerDomain,principal.getName());
    }

    @PutMapping(value = "/resource/user/post/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Mono<ContainerDomain> updatePost(@PathVariable("id") String id,
                                              @RequestBody ContainerDomain containerDomain, Principal principal){
        return postService.updatePostUser(id,containerDomain,principal);

    }

    @PutMapping(value = "/resource/admin/post/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Mono<?> updatePostAdmin(@PathVariable("id") String id,
                              @RequestBody @Valid ContainerDomain containerDomain, Principal principal){
        return postService.updatePostAdmin(id,containerDomain,principal);

    }

    @DeleteMapping(value = "/resource/user/post/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public Mono<Void> deletePost(@PathVariable("id") String id, Principal principal){
        return postService.deletePostAdminUser(id,principal);
    }
    @DeleteMapping(value = "/resource/admin/post/{id}")
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