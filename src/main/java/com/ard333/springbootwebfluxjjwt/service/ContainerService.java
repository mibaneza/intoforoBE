package com.ard333.springbootwebfluxjjwt.service;

import com.ard333.springbootwebfluxjjwt.domain.ContainerDomain;
import com.ard333.springbootwebfluxjjwt.domain.UpdateDomain;
import com.ard333.springbootwebfluxjjwt.model.Duall;
import com.ard333.springbootwebfluxjjwt.model.Tri;
import com.ard333.springbootwebfluxjjwt.repository.*;
import com.ard333.springbootwebfluxjjwt.service.util.Getdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class ContainerService {
    @Autowired
    Getdate getdate;

    @Autowired
    private ContainerRepository containerRepository;

    @Autowired
    private UpdatePostRepository updatePostRepository;

    @Autowired
    private CategoriesService categoriesService;

    @Autowired
    private UpdatePostService updatetService;

    @Autowired
    private UserService userService;

    public Mono<ContainerDomain> findByLinkTitle(String linktitle){
        return containerRepository.findByLinktitle(linktitle);
    }
    public Mono<ContainerDomain> saveContainer(Tri tri) {
        return categoriesService.updateCategoriePost(tri.getPostDomain().getIdcategoria(),tri.getPostDomain().getIdpost())
                .map((aea) -> new Duall(tri.getPostDomain().getIduser(),"post"))
                .flatMap(userService::findByUsernameUpdateComentPost)
                .map((updateDomain) -> {
                    tri.getContainerDomain().setIdpost(tri.getPostDomain().getIdpost());
                    tri.getContainerDomain().setEst(Boolean.TRUE);
                    tri.getContainerDomain().setUpdateModel(tri.getPostDomain().getUpdateModel());
                    tri.getContainerDomain().setLinktitlecategory(tri.getPostDomain().getLinktitlecategory());
                    tri.getContainerDomain().setQuantitycommets((long) 0);
                    return tri.getContainerDomain();
                })
                .flatMap(containerRepository::save)
                ;
    }

    public Mono<ContainerDomain> updateContainer(Tri tri) {
        return  categoriesService.updateCategoriePost(tri.getPostDomain().getIdcategoria(),tri.getPostDomain().getIdpost())
                .map((aea) -> tri.getDuall())
                .flatMap(updatetService::upUpdateDomain)
                .map((ga) -> tri.getPostDomain().getLinktitle())
                .flatMap(containerRepository::findByLinktitle)
                .map((contianerone) -> {
                    contianerone.setContent(tri.getContainerDomain().getContent());
                    contianerone.setLinktitle(tri.getContainerDomain().getLinktitle());
                    contianerone.setTitle(tri.getContainerDomain().getTitle());
                    return contianerone;
                })
                .flatMap(containerRepository::save)
                ;
    }
    public Mono<ContainerDomain> updateAdminContainer(Tri tri) {
        return  categoriesService.updateCategoriePost(tri.getPostDomain().getIdcategoria(),tri.getPostDomain().getIdpost())
                .map((aea) -> tri.getDuall())
                .flatMap(updatetService::upUpdateDomain)
                .map((ga) -> tri.getPostDomain().getLinktitle())
                .flatMap(containerRepository::findByLinktitle)
                .map((contianerone) -> {
                    contianerone.setContent(tri.getContainerDomain().getContent());
                    contianerone.setLinktitle(tri.getContainerDomain().getLinktitle());
                    contianerone.setEst(tri.getContainerDomain().getEst());
                    contianerone.setTitle(tri.getContainerDomain().getTitle());
                    return contianerone;
                })
                .flatMap(containerRepository::save)
                ;
    }

}
