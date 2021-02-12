package com.ard333.springbootwebfluxjjwt.service;

import com.ard333.springbootwebfluxjjwt.domain.ContainerDomain;
import com.ard333.springbootwebfluxjjwt.domain.UpdateDomain;
import com.ard333.springbootwebfluxjjwt.model.Duall;
import com.ard333.springbootwebfluxjjwt.model.Tri;
import com.ard333.springbootwebfluxjjwt.repository.*;
import com.ard333.springbootwebfluxjjwt.service.util.Getdate;
import org.springframework.beans.factory.annotation.Autowired;
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


    public Mono<ContainerDomain> saveContainer(Tri tri) {
        Date date = getdate.date();
        return userService.findByUsernameUpdateComentPost(new Duall(tri.getPostDomain().getIduser(),"post"))
                .map((user) ->
                        new UpdateDomain(
                        tri.getPostDomain().getUpdateModel(),
                        tri.getPostDomain().getIdpost(),
                        date,
                        date
                ))
                .flatMap(updatePostRepository::save)
                .map((updateDomain) -> {
                    tri.getContainerDomain().setIdpost(tri.getPostDomain().getIdpost());
                    tri.getContainerDomain().setEst(Boolean.TRUE);
                    tri.getContainerDomain().setIdupdate(updateDomain.getIdupdateposts());
                    return tri.getContainerDomain();
                })
                .flatMap(containerRepository::save)
                ;
    }

    public Mono<ContainerDomain> updateContainer(Tri tri) {
        return  categoriesService.updateCategoriePost(tri.getPostDomain().getIdcategoria(),tri.getPostDomain().getIdpost())
                .map((aea) -> tri.getDuall())
                .flatMap(updatetService::upUpdateDomain)
                .map((update) -> {
                    tri.getContainerDomain().setIdupdate(update.getIdupdateposts());
                    tri.getContainerDomain().setEst(Boolean.TRUE);
                    return tri.getContainerDomain();
                })
                .flatMap(containerRepository::save)
                ;
    }
    public Mono<ContainerDomain> updateAdminContainer(Tri tri) {
        return  categoriesService.updateCategoriePost(tri.getPostDomain().getIdcategoria(),tri.getPostDomain().getIdpost())
                .map((aea) -> tri.getDuall())
                .flatMap(updatetService::upUpdateDomain)
                .map((update) -> {
                    tri.getContainerDomain().setIdupdate(update.getIdupdateposts());
                    return tri.getContainerDomain();
                })
                .flatMap(containerRepository::save)
                ;
    }

}
