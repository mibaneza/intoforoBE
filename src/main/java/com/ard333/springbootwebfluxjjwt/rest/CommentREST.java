package com.ard333.springbootwebfluxjjwt.rest;

import com.ard333.springbootwebfluxjjwt.domain.CommentsDomain;
import com.ard333.springbootwebfluxjjwt.domain.PostDomain;
import com.ard333.springbootwebfluxjjwt.service.CommentsService;
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
public class CommentREST {
    @Autowired
    Getdate getdate;

    @Autowired
    CommentsService commentsService;

    @GetMapping(value = "/api/web/comment/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public Flux<CommentsDomain> readAllandPost(@PathVariable("id") String id){
        return commentsService.findAllCommentsAndPost(id);
    }

    @PostMapping(value = "/resource/us/comment/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Mono<CommentsDomain> createUserComment(@PathVariable("id") String id, Principal principal,
                                         @RequestBody @Valid CommentsDomain commentsDomain){
        String[] arrSplit = principal.getName().split(",");
        return commentsService.saveCommentUser(commentsDomain, id, arrSplit[0], arrSplit[1]);
    }
    @PostMapping(value = "/resource/ad/comment/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Mono<CommentsDomain> createAdminComment(@PathVariable("id") String id, Principal principal,
                                                  @RequestBody @Valid CommentsDomain commentsDomain){
        String[] arrSplit = principal.getName().split(",");
        return commentsService.saveCommentAdmin(commentsDomain, id, arrSplit[0], arrSplit[1]);
    }

    @PutMapping(value = "/resource/us/comment/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Mono<?> updatePost(@PathVariable("id") String id,
                              @RequestBody @Valid CommentsDomain commentsDomain, Principal principal){
        return commentsService.updateCommentUser(id,commentsDomain,principal);

    }

    @PutMapping(value = "/resource/ad/comment/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Mono<?> updatePostAdmin(@PathVariable("id") String id,
                                   @RequestBody @Valid CommentsDomain commentsDomain){
        return commentsService.updateCommentAdmin(id,commentsDomain);

    }

    @DeleteMapping(value = "/resource/comment/{id}/{idpost}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public Mono<Void> deletePost(@PathVariable("id") String id, @PathVariable("idpost") String idpost, Principal principal){
        String[] arrSplit = principal.getName().split(",");
        return commentsService.deleteCommentsUser(id,idpost,arrSplit[0]);
    }


    @DeleteMapping(value = "/resource/ad/comment/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public Mono<Void> deletePostAdmin(@PathVariable("id") String id){
        return commentsService.deleteCommentsAdmin(id);
    }


}
