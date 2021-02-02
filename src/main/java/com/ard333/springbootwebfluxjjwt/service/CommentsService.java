package com.ard333.springbootwebfluxjjwt.service;

import com.ard333.springbootwebfluxjjwt.domain.CategoriesDomain;
import com.ard333.springbootwebfluxjjwt.domain.CommentsDomain;
import com.ard333.springbootwebfluxjjwt.repository.CategoriesRepository;
import com.ard333.springbootwebfluxjjwt.repository.CommentsRepository;
import com.ard333.springbootwebfluxjjwt.repository.PostRepository;
import com.ard333.springbootwebfluxjjwt.service.util.Getdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.security.Principal;

@Service
public class CommentsService {


    @Autowired
    Getdate getdate;

    @Autowired
    CommentsRepository commentsRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    private UserService userService;

    public Flux<CommentsDomain> findAllComments(){
        return commentsRepository.findAll();
    }
    public Flux<CommentsDomain> findAllCommentsAndPost(String idpost){
        return commentsRepository.findByComentpostOrderByIndat(idpost);
    }
    public Mono<Long> countByNickuserComents(String iduser){
        return commentsRepository.countByNickuser(iduser);
    }
    public Mono<Long> countByNickuserPost(String iduser){
        return postRepository.countByIduser(iduser);
    }


    public Mono<CommentsDomain> saveCommentUser(CommentsDomain commentsDomain, String idpost, String iduser, String avatar){
        return postRepository.findByIdpostAndEst(idpost,true)
                .map((cd) ->
                new CommentsDomain(commentsDomain.getComment(),cd.getIdpost(),iduser,avatar,getdate.date())
                )
                .flatMap(commentsRepository::save)
                .map((awd) -> userService.findByUsernameUpdateComentPost(iduser,"comment"))
                .map((awd) -> new CommentsDomain(commentsDomain.getComment(),idpost,iduser,avatar,getdate.date()))
                ;
    }

    public Mono<CommentsDomain> saveCommentAdmin(CommentsDomain commentsDomain, String idpost, String iduser, String avatar){
        return postRepository.findById(idpost)
                .map((cd) ->
                        new CommentsDomain(commentsDomain.getComment(),cd.getIdpost(),iduser,avatar,getdate.date())
                )
                .flatMap(commentsRepository::save)
                .map((awd) -> userService.findByUsernameUpdateComentPost(iduser,"comment"))
                .map((awd) -> new CommentsDomain(commentsDomain.getComment(),idpost,iduser,avatar,getdate.date()))
                ;
    }


    public Mono<CommentsDomain> findId(String id){
        return commentsRepository.findById(id);
    }

    public Mono<CommentsDomain> updateCommentUser(String idpost ,CommentsDomain commentsDomain, Principal principal){
        String[] arrSplit = principal.getName().split(",");
        return commentsRepository.findByNickuserAndComentpostAndIdcoment(arrSplit[0],idpost,commentsDomain.getIdcoment())
                .map(c -> commentsDomain)
                .flatMap(commentsRepository::save);
    }
    public Mono<CommentsDomain> updateCommentAdmin(String id ,CommentsDomain commentsDomain){
        return commentsRepository.findById(id)
                .map(c -> commentsDomain)
                .flatMap(commentsRepository::save);
    }

    public Mono<Void> deleteCommentsUser(String idpost, String idcomment, String iduser){

        return  commentsRepository.findByNickuserAndComentpostAndIdcoment(iduser,idpost,idcomment)
                .switchIfEmpty(Mono.error(Exception::new))
                .flatMap(commentsRepository::delete);
    }
    public Mono<Void> deleteCommentsAdmin(String id){
        return  commentsRepository.findById(id)
                .switchIfEmpty(Mono.error(Exception::new))
                .flatMap(commentsRepository::delete);
    }

}
