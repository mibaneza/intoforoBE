package com.ard333.springbootwebfluxjjwt.service;

import com.ard333.springbootwebfluxjjwt.domain.PostDomain;
import com.ard333.springbootwebfluxjjwt.domain.UpdateDomain;
import com.ard333.springbootwebfluxjjwt.repository.UpdatePostRepository;
import com.ard333.springbootwebfluxjjwt.service.util.Getdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UpdatePostService {
    @Autowired
    Getdate getdate;
    @Autowired
    private UpdatePostRepository updatePostRepository;

    public Mono<?> findColleUpdate(String iduser ,String avatar,String idpost){
        return  updatePostRepository.findByIdcolleAndUsernameAndTitle(idpost,iduser,"ACTUALIZADO").map((up) -> {
                    up.setUpdat(getdate.date());
                    return up;
                }).defaultIfEmpty(new UpdateDomain(
                        "ACTUALIZADO",
                        iduser,
                        avatar,
                        idpost,
                        getdate.date(),
                        getdate.date()))
                        .flatMap(updatePostRepository::save);

    }

    public List<UpdateDomain> listUpDomain(List<UpdateDomain> updateDomains,String iduser,String avatar){
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
                           avatar,
                           getdate.date(),
                           getdate.date()));
               }
            }
        }
        return listupdateDomains;
    }
    public UpdateDomain listUpDoawdwmain(List<UpdateDomain> updateDomains,String iduser,String avatar){
        UpdateDomain update = null;
        boolean ga = true;
        for (UpdateDomain up : updateDomains){
            if (up.getTitle() != "ACTUALIZADO" && up.getTitle() != "INICIADO" && up.getUsername() != iduser) {
                ga = false;
                update = new UpdateDomain(
                        "ACTUALIZADO",
                        iduser,
                        avatar,
                        getdate.date(),
                        getdate.date());
            }
        }
        return update;
    }

}
