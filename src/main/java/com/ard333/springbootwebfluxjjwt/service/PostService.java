package com.ard333.springbootwebfluxjjwt.service;

import com.ard333.springbootwebfluxjjwt.domain.CategoriesDomain;
import com.ard333.springbootwebfluxjjwt.domain.PostDomain;
import com.ard333.springbootwebfluxjjwt.domain.UpdateDomain;
import com.ard333.springbootwebfluxjjwt.model.Duall;
import com.ard333.springbootwebfluxjjwt.repository.CategoriesRepository;
import com.ard333.springbootwebfluxjjwt.repository.PostRepository;
import com.ard333.springbootwebfluxjjwt.repository.UpdatePostRepository;
import com.ard333.springbootwebfluxjjwt.repository.UserRepository;
import com.ard333.springbootwebfluxjjwt.service.util.Getdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Service
public class PostService {
    @Autowired
    Getdate getdate;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UpdatePostRepository updatePostRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoriesRepository categoriesRepository;

    @Autowired
    private CategoriesService categoriesService;

    @Autowired
    private UpdatePostService UpdatetService;

    @Autowired
    private UserService userService;
    public Mono<PostDomain> findId(String id){
        return postRepository.findById(id);
    }

    public Flux<PostDomain> findAllPosts(){
        return postRepository.findAll();
    }
    public Flux<PostDomain> findAllPostCategories(String cate){
        return postRepository.findByIdcategoria(cate);
    }
    public Mono<PostDomain> saveUserPost(String id , PostDomain postDomain, Principal principal) {
        String[] arrSplit = principal.getName().split(",");
        return categoriesRepository.findByIdcategoriesAndEst(id,true)
                .map((c) -> new PostDomain(
                            arrSplit[0],
                            postDomain.getTitlePost(),
                            postDomain.getContainerhtml(),
                            c.getIdcategories(),
                           true,
                           "awdawd",
                            postDomain.getLinktitle()
                    )
                )
                .flatMap(postRepository::save)
                .map(c -> new Duall(id,c.getIdpost()))
                .flatMap(categoriesService::updateCategoriePost)
                .map((p) -> new UpdateDomain(
                            "INICIADO",
                            arrSplit[0],
                            arrSplit[1],
                            arrSplit[2],
                            p.getIdpost(),
                            getdate.date(),
                            getdate.date()
                    )
                )
                .flatMap(updatePostRepository::save)
                .map((c) -> new PostDomain(
                                c.getIdcolle(),
                                arrSplit[0],
                                postDomain.getLinktitle(),
                                postDomain.getTitlePost(),
                                postDomain.getContainerhtml(),
                                id,
                                true,
                                c.getIdupdateposts()

                        )
                )
                .flatMap(postRepository::save)
                .map((tw) -> userService.findByUsernameUpdateComentPost(arrSplit[0],"post"))
                .map((ga) -> new PostDomain(
                            arrSplit[0],
                            postDomain.getTitlePost(),
                            postDomain.getContainerhtml()
                    )
                );


    }
    public Mono<PostDomain> saveAdminPost(String id , PostDomain postDomain, Principal principal) {
        String[] arrSplit = principal.getName().split(",");
        return categoriesRepository.findById(id)
                .map((c) -> new PostDomain(
                                arrSplit[0],
                                postDomain.getTitlePost(),
                                postDomain.getContainerhtml(),
                                c.getIdcategories(),
                                true,
                                "awdawd",
                                postDomain.getLinktitle()
                        )
                )
                .flatMap(postRepository::save)
                .map(c -> new Duall(id,c.getIdpost()))
                .flatMap(categoriesService::updateCategoriePost)
                .map((p) -> new UpdateDomain(
                                "INICIADO",
                                arrSplit[0],
                                arrSplit[1],
                                arrSplit[2],
                                p.getIdpost(),
                                getdate.date(),
                                getdate.date()
                        )
                )
                .flatMap(updatePostRepository::save)
                .map((c) -> new PostDomain(
                                c.getIdcolle(),
                                arrSplit[0],
                                postDomain.getLinktitle(),
                                postDomain.getTitlePost(),
                                postDomain.getContainerhtml(),
                                id,
                                true,
                                c.getIdupdateposts()

                        )
                )
                .flatMap(postRepository::save)
                .map((tw) -> userService.findByUsernameUpdateComentPost(arrSplit[0],"post"))
                .map((ga) -> new PostDomain(
                                arrSplit[0],
                                postDomain.getTitlePost(),
                                postDomain.getContainerhtml()
                        )
                );


    }
    public Mono<PostDomain>  updatePostUser(String idpost , PostDomain postDomain, Principal principal){
        String[] arrSplit = principal.getName().split(",");
        return  postRepository.findByIdpostAndIduser(idpost,arrSplit[0])
                .map( (p) -> {
                    p.setTitlePost(postDomain.getTitlePost());
                    p.setContainerhtml(postDomain.getContainerhtml());
                    return p;
                })
                .flatMap(postRepository::save)
                .map(c -> new Duall(c.getIdcategoria(),c.getIdpost()))
                .flatMap(categoriesService::updateCategoriePost)
                .map((awd) -> new Duall(principal.getName(),awd.getIdpost()))
                .flatMap(UpdatetService::upUpdateDomain)
                .map((upps) -> updatePostdate(upps.getIdcolle(),upps.getIdupdateposts()))
                //.map((upps) -> new Duall(upps.getIdcolle(),upps.getIdupdateposts()))
                //.flatMap(UpdatetService::updatePostdate)
                ;
    }

    public Mono<PostDomain> updatePostAdmin(String idpost , PostDomain postDomain, Principal principal){
        String[] arrSplit = principal.getName().split(",");
        return postRepository.findById(idpost)
                .map( (p) -> {
                    p.setTitlePost(postDomain.getTitlePost());
                    p.setContainerhtml(postDomain.getContainerhtml());
                    p.setEst(postDomain.getEst());
                    return p;
                })
                .flatMap(postRepository::save)
                .map(c -> new Duall(c.getIdcategoria(),c.getIdpost()))
                .flatMap(categoriesService::updateCategoriePost)
                .map((awd) -> new Duall(principal.getName(),awd.getIdpost()))
                .flatMap(UpdatetService::upUpdateDomain)
                .map((upps) -> updatePostdate(upps.getIdcolle(),upps.getIdupdateposts()))
                //.map((upps) -> new Duall(upps.getIdcolle(),upps.getIdupdateposts()))
                //.flatMap(UpdatetService::updatePostdate)
                ;

    }

    public PostDomain  updatePostdate(String idpost ,String idupdate ){
        return  postRepository.findById(idpost )
                .map( (p) -> {
                    p.setIdupdate(idupdate );
                    return p;
                })
                .flatMap(postRepository::save).block()
                ;
    }







/*

    public Mono<PostDomain> savePost(String id , PostDomain postDomain, Principal principal) {
        return categoriesRepository.findById(id)
                .map((c) -> {
                    String[] arrSplit = principal.getName().split(",");
                    return new PostDomain(
                            arrSplit[0],
                            postDomain.getTitlePost(),
                            postDomain.getContainerhtml(),
                            c.getIdcategories(),
                            postDomain.getEst(),
                            new UpdateDomain(
                                    "INICIADO",
                                    arrSplit[0],
                                    arrSplit[1],
                                    getdate.date(),
                                    getdate.date()
                            )
                    );
                }).flatMap(postRepository::save);


    }


    public Mono<PostDomain>  updatePostUser(String idpost , PostDomain postDomain, Principal principal){
        String[] arrSplit = principal.getName().split(",");
        return  postRepository.findByIdpostAndIduser(idpost,arrSplit[0])
                .map( (p) -> {
                    List<UpdateDomain> updateDomains = new ArrayList<>();

                    p.setTitlePost(postDomain.getTitlePost());
                    p.setContainerhtml(postDomain.getContainerhtml());
                    if(p.getUpdomains() == null || p.getUpdomains().size() == 0)
                    {
                        updateDomains.add(new UpdateDomain(
                                "ACTUALIZADO",
                                arrSplit[0],
                                arrSplit[1],
                                getdate.date(),
                                getdate.date()));
                    }
                    else{
                        updateDomains.addAll(UpdatetService.listUpDomain(p.getUpdomains(),arrSplit[0],arrSplit[1]));
                    }
                    p.setUpdomains(updateDomains);
                    return p;
                })
                .flatMap(postRepository::save);

    }
    public Mono<PostDomain> updatePostAdmin(String idpost , PostDomain postDomain, Principal principal){
        String[] arrSplit = principal.getName().split(",");
        return postRepository.findById(idpost)
                .map( (p) -> {
                    List<UpdateDomain> updateDomains = new ArrayList<>();
                    p.setTitlePost(postDomain.getTitlePost());
                    p.setContainerhtml(postDomain.getContainerhtml());
                    p.setEst(postDomain.getEst());
                    if(p.getUpdomains() == null || p.getUpdomains().size() == 0)
                    {
                        updateDomains.add(new UpdateDomain(
                                "ACTUALIZADO",
                                arrSplit[0],
                                arrSplit[1],
                                getdate.date(),
                                getdate.date()));
                    }
                    else{
                        updateDomains.addAll(UpdatetService.listUpDomain(p.getUpdomains(),arrSplit[0],arrSplit[1]));
                    }
                    p.setUpdomains(updateDomains);
                    return p;
                }).flatMap(postRepository::save);
    }
*/


    public Mono<Void> deletePostAdminUser(String id, Principal principal){
        String[] arrSplit = principal.getName().split(",");
        return  postRepository.findByIdpostAndIduser(id,arrSplit[0])
                .switchIfEmpty(Mono.error(Exception::new))
                .flatMap(postRepository::delete);
    }

    public Mono<Void> deletePostAdmin(String id){
        return  postRepository.findById(id)
                .switchIfEmpty(Mono.error(Exception::new))
                .flatMap(postRepository::delete);
    }


}
