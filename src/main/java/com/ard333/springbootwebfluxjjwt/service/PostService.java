package com.ard333.springbootwebfluxjjwt.service;

import com.ard333.springbootwebfluxjjwt.domain.*;
import com.ard333.springbootwebfluxjjwt.model.Duall;
import com.ard333.springbootwebfluxjjwt.model.Tri;
import com.ard333.springbootwebfluxjjwt.model.UpdateModel;
import com.ard333.springbootwebfluxjjwt.repository.*;
import com.ard333.springbootwebfluxjjwt.service.util.Getdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.security.Principal;

@Service
public class PostService {
    @Autowired
    Getdate getdate;

    @Autowired
    private PostRepository postRepository;


    @Autowired
    private CategoriesRepository categoriesRepository;

    @Autowired
    private ContainerService containerService;

    private String[] arrSplit;
    public Mono<PostDomain> findId(String id){
        return postRepository.findById(id);
    }
    public Long countByPost(String idcate){
        return postRepository.countByIdcategoria(idcate).block();
    }
    public Flux<PostDomain> findAllPosts(){
        return postRepository.findAll();
    }
    public Flux<PostDomain> findAllPostCategories(String cate){
        return postRepository.findByIdcategoria(cate);
    }
    public Mono<ContainerDomain> saveUserPost(String id , ContainerDomain containerDomain, String principal) {
         arrSplit = principal.split(",");
        return categoriesRepository.findByIdcategoriesAndEst(id,true)
                .map((c) -> new PostDomain(
                                arrSplit[0],
                                containerDomain.getLinktitle(),
                                containerDomain.getTitle(),
                                true,
                                c.getIdcategories(),
                                new UpdateModel("INICIADO", arrSplit[0], arrSplit[1], arrSplit[2], arrSplit[3],getdate.date(),getdate.date()),
                                c.getLinktitle()
                        )
                )
                .flatMap(postRepository::save)
                .map((p) -> new Tri(p,containerDomain))
                .flatMap(containerService::saveContainer);
    }

    public Mono<ContainerDomain> saveAdminPost(String id , ContainerDomain containerDomain, String principal) {
       arrSplit = principal.split(",");
        return categoriesRepository.findById(id)
                .map((c) -> new PostDomain(
                                arrSplit[0],
                                containerDomain.getLinktitle(),
                                containerDomain.getTitle(),
                                containerDomain.getEst(),
                                c.getIdcategories(),
                                new UpdateModel("INICIADO", arrSplit[0], arrSplit[1], arrSplit[2], arrSplit[3]),
                                c.getLinktitle()
                        )
                )
                .flatMap(postRepository::save)
                .map((p) -> new Tri(p,containerDomain))
                .flatMap(containerService::saveContainer);
    }





    public Mono<ContainerDomain>  updatePostUser(String idpost , ContainerDomain containerDomain, Principal principal){
        arrSplit = principal.getName().split(",");
        return  postRepository.findByIdpostAndIduser(idpost,arrSplit[0])
                .map( (p) -> {
                    p.setTitlePost(containerDomain.getTitle());
                    p.setLinktitle(containerDomain.getLinktitle());
                    p.setUpdateModel(new UpdateModel("ACTUALIZADO", arrSplit[0], arrSplit[1], arrSplit[2], arrSplit[3]));
                    return p;
                })
                .flatMap(postRepository::save)
                .map((p) -> new Tri(p,containerDomain,new Duall(principal.getName(), p.getIdpost())))
                .flatMap(containerService::updateContainer)
                ;
    }
    public Mono<ContainerDomain>  updatePostAdmin(String idpost , ContainerDomain containerDomain, Principal principal){
        arrSplit = principal.getName().split(",");
        return  postRepository.findById(idpost)
                .map( (p) -> {
                    p.setTitlePost(containerDomain.getTitle());
                    p.setLinktitle(containerDomain.getLinktitle());
                    p.setUpdateModel(new UpdateModel("ACTUALIZADO", arrSplit[0], arrSplit[1], arrSplit[2], arrSplit[3]));
                    return p;
                })
                .flatMap(postRepository::save)
                .map((p) -> new Tri(p,containerDomain,new Duall(principal.getName(), p.getIdpost())))
                .flatMap(containerService::updateAdminContainer)
                ;
    }


/*
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
                .flatMap(postRepository::delete)
                .map((aea) -> {
                    return id;
                })
                .flatMap(containerService:deleteContainer)
                ;
    }

    public Mono<Void> deletePostAdmin(String id){
        return  postRepository.findById(id)
                .switchIfEmpty(Mono.error(Exception::new))
                .flatMap(postRepository::delete);
    }


}
