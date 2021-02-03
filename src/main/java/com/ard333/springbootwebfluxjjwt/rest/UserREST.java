package com.ard333.springbootwebfluxjjwt.rest;

import com.ard333.springbootwebfluxjjwt.domain.PostDomain;
import com.ard333.springbootwebfluxjjwt.model.UserInfo;
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
public class UserREST {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/api/web/user/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public Mono<UserInfo> readAsll(@PathVariable("id") String id){
        return userService.findUsername(id);
    }

}
