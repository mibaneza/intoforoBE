package com.ard333.springbootwebfluxjjwt.rest;


import com.ard333.springbootwebfluxjjwt.domain.UserDomain;
import com.ard333.springbootwebfluxjjwt.security.JWTUtil;
import com.ard333.springbootwebfluxjjwt.security.PBKDF2Encoder;
import com.ard333.springbootwebfluxjjwt.security.model.AuthRequest;
import com.ard333.springbootwebfluxjjwt.security.model.AuthResponse;
import com.ard333.springbootwebfluxjjwt.service.DiscordService;
import com.ard333.springbootwebfluxjjwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

/**
 *
 * @author ard333
 */
@RestController
public class AuthenticationREST {

	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private PBKDF2Encoder passwordEncoder;

	@Autowired
	private UserService userService;

	@Autowired

	private DiscordService discordService;
	@RequestMapping(value = "/api/web/awd", method = RequestMethod.GET)
	public Date loawdawdgin() {
		return userService.datByUser("486290136267489300");
	}

	@RequestMapping(value = "/api/web/login", method = RequestMethod.POST)
	public Mono<ResponseEntity<?>> login(@RequestBody String token) {
		com.ard333.springbootwebfluxjjwt.security.discord.model.User userDiscord = discordService.getUserDiscord(token);
		System.out.println(userDiscord);
			return userService.getTokenAndUsername(userDiscord);
	}
/*
	public Mono<ResponseEntity<?>> register(com.ard333.springbootwebfluxjjwt.security.discord.model.User userDiscord) {

		return userService.findByUsername(userDiscord).map((userDetails) -> {
			if (passwordEncoder.encode(userDiscord.getEmail()).equals(userDetails.getPassword())) {
				return ResponseEntity.ok(new AuthResponse(userService.save(userDiscord),jwtUtil.generateToken(userDetails)));
			} else {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			}
		}).defaultIfEmpty(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}
*/

	@RequestMapping(value = "/logins", method = RequestMethod.GET)
	public Flux<UserDomain> getuSer(){
		return userService.getUSer();
	}



}
