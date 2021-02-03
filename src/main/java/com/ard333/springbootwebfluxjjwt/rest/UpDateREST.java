package com.ard333.springbootwebfluxjjwt.rest;

import com.ard333.springbootwebfluxjjwt.domain.UpdateDomain;
import com.ard333.springbootwebfluxjjwt.model.UserInfo;
import com.ard333.springbootwebfluxjjwt.service.UpdatePostService;
import com.ard333.springbootwebfluxjjwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class UpDateREST {
    @Autowired
    private UpdatePostService updatePostService;

    @GetMapping(value = "/api/web/postupdatelist/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public Flux<UpdateDomain> readAsll(@PathVariable("id") String id){
        return updatePostService.findByIdcolleOrderByUpdatAsc(id);
    }

}