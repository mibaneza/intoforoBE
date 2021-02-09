package com.ard333.springbootwebfluxjjwt.service;

import com.ard333.springbootwebfluxjjwt.domain.PostDomain;
import com.ard333.springbootwebfluxjjwt.domain.UpdateDomain;
import com.ard333.springbootwebfluxjjwt.model.Duall;
import com.ard333.springbootwebfluxjjwt.repository.CommentsRepository;
import com.ard333.springbootwebfluxjjwt.repository.PostRepository;
import com.ard333.springbootwebfluxjjwt.repository.UpdatePostRepository;
import com.ard333.springbootwebfluxjjwt.service.util.Getdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UpdatePostService {
    @Autowired
    CommentsRepository commentsRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    Getdate getdate;
    @Autowired
    private UpdatePostRepository updatePostRepository;

    public Mono<?> findColleUpdate(String iduser,String idpost){
        return  updatePostRepository.findByIdcolleAndUsernameAndTitle(idpost,iduser,"ACTUALIZADO").map((up) -> {
                    up.setUpdat(getdate.date());
                    return up;
                }).defaultIfEmpty(new UpdateDomain(
                        "ACTUALIZADO",
                        iduser,
                        idpost,
                        getdate.date(),
                        getdate.date()))
                        .flatMap(updatePostRepository::save);

    }
    public String findByIdTitle(String idcoole){
        return updatePostRepository.findById(idcoole).map(UpdateDomain::getTitle).block();
    }
    public Flux<UpdateDomain> findByIdcolleOrderByUpdatAsc(String idcoole){
        return updatePostRepository.findByIdcolleOrderByUpdatAsc(idcoole);
    }

    public List<UpdateDomain> listUpDomain(List<UpdateDomain> updateDomains,String iduser){
        List<UpdateDomain> listupdateDomains = new ArrayList<>();

        for (UpdateDomain up : updateDomains){
           if (!up.getUsername().equals(iduser)){
                    listupdateDomains.add(up);
            } else{
               if (up.getTitle().equals("ACTUALIZADO")) {
                   up.setUpdat(getdate.date());
                   listupdateDomains.add(up);
               }else{
                   listupdateDomains.add(new UpdateDomain(
                           "ACTUALIZADO",
                           iduser,
                           getdate.date(),
                           getdate.date()));
               }
            }
        }
        return listupdateDomains;
    }
    public UpdateDomain listUpDoawdwmain(List<UpdateDomain> updateDomains,String iduser){
        UpdateDomain update = null;
        boolean ga = true;
        for (UpdateDomain up : updateDomains){
            if (up.getTitle() != "ACTUALIZADO" && up.getTitle() != "INICIADO" && up.getUsername() != iduser) {
                ga = false;
                update = new UpdateDomain(
                        "ACTUALIZADO",
                        iduser,
                        getdate.date(),
                        getdate.date());
            }
        }
        return update;
    }
    public Mono<UpdateDomain> upUpdateDomain(Duall duall){
       return  updatePostRepository.findByIdcolleAndUsernameAndTitle(duall.getDualv2(),duall.getDualv1(),"ACTUALIZADO")
               .map((upU) -> {
                   upU.setUpdat(getdate.date());
                   return upU;
                   })
               .defaultIfEmpty(new UpdateDomain(
                       "ACTUALIZADO",
                       duall.getDualv1(),
                       duall.getDualv2(),
                       getdate.date(),
                       getdate.date()))
               .flatMap(updatePostRepository::save);
    }
    public Mono<PostDomain>  updatePostdate(Duall duall ){
        return  postRepository.findById(duall.getDualv1())
                .map( (p) -> {
                    p.setIdupdate(duall.getDualv2());
                    return p;
                })
                .flatMap(postRepository::save)
                ;
    }
        /*

        return updatePostRepository.findByIdcolleAndUsernameAndTitle(idpost,iduser,"ACTUALIZADO")
                .map((up) -> {
                    System.out.println("Awdawdawdwddwadawdawdawdadwawdw");
                    System.out.println("Awdawdawdwddwadawdawdawdadwawdw");
                    System.out.println("Awdawdawdwddwadawdawdawdadwawdw");
                    System.out.println("Awdawdawdwddwadawdawdawdadwawdw");
                    System.out.println(up);
                    up.setUpdat(getdate.date());
                    return up;
                })
                .defaultIfEmpty(
                        new UpdateDomain(
                                "ACTUALIZADO",
                                iduser,
                                idpost,
                                getdate.date(),
                                getdate.date()
                        )
                )
                .flatMap(updatePostRepository::save)
                .map((ppu) -> new PostDomain(
                        iduser,
                        titlepost,
                        html
                        )).block();

    }*/
}
