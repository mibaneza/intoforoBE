package com.ard333.springbootwebfluxjjwt.rest;

import com.ard333.springbootwebfluxjjwt.domain.UserDomain;
import com.ard333.springbootwebfluxjjwt.model.Message;
import com.ard333.springbootwebfluxjjwt.service.DiscordService;
import com.ard333.springbootwebfluxjjwt.service.UserService;/*
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.requests.RestAction;*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.security.Principal;

/**
 *
 * @author ardiansyah
 */
@RestController
public class ResourceREST {
	@Autowired
	private DiscordService discordService;
	@Autowired
	private UserService userService;
	@RequestMapping(value = "/resource/user", method = RequestMethod.GET)
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public Mono<ResponseEntity<?>> user(Principal user) {
		return Mono.just(ResponseEntity.ok(user));
	}
	
	@RequestMapping(value = "/resource/admin", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN')")
	public Mono<ResponseEntity<?>> admin() {
		return Mono.just(ResponseEntity.ok(new Message("Content for admin")));
	}
	
	@RequestMapping(value = "/resource/user-or-admin", method = RequestMethod.GET)
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public Mono<ResponseEntity<?>> userOrAdmin() {
		return Mono.just(ResponseEntity.ok(new Message("Content for user or admin")));
	}

/*

	@RequestMapping(value = "/resource/save", method = RequestMethod.POST)
	@PreAuthorize("hasRole('USER')")
	public Mono<ResponseEntity<?>> userSave(@RequestBody String token) {
		com.ard333.springbootwebfluxjjwt.security.discord.model.User userDiscord = discordService.getUserDiscord(token);
		return Mono.just(ResponseEntity.ok(userService.save(userDiscord)));
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	//@PreAuthorize("hasRole('USER')")
	public  ResponseEntity<?>findByIdFromServerDiscord() throws Exception{
		return discordService.retrieveUserById();

	}

*/

	@RequestMapping(value = "/resource/userall", method = RequestMethod.GET)
	@PreAuthorize("hasRole('USER')")
	public Flux<UserDomain> userALL() {
		return userService.getUSer();
	}

}
