package com.ard333.springbootwebfluxjjwt.service;


import com.ard333.springbootwebfluxjjwt.domain.UserDomain;
import com.ard333.springbootwebfluxjjwt.model.User;
import com.ard333.springbootwebfluxjjwt.repository.UserRepository;
import com.ard333.springbootwebfluxjjwt.security.JWTUtil;
import com.ard333.springbootwebfluxjjwt.security.PBKDF2Encoder;
import com.ard333.springbootwebfluxjjwt.security.model.Role;
import java.util.Arrays;
import java.util.List;

import com.ard333.springbootwebfluxjjwt.security.model.UserMapperModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 *
 * @author ard333
 */
@Service
public class UserService {
	
	// this is just an example, you can load the user from the database from the repository

	@Autowired
	private JWTUtil jwtUtil;

	@Autowired
	private PBKDF2Encoder passwordEncoder;

	@Autowired
	private UserRepository userRepository;


	public Mono<User> TokenFindByUsername(com.ard333.springbootwebfluxjjwt.security.discord.model.User userDiscord) {
		return userRepository.findByUsername(userDiscord.getId().toString())
				.map((user) ->  new User(user.getUsername()+","+user.getAvatar(),user.getPassword(),user.getEnabled(),user.getRoles()));
	}
	public Mono<UserDomain> register(com.ard333.springbootwebfluxjjwt.security.discord.model.User userDiscord) {

		return 	userRepository.save(new UserDomain(
				userDiscord.getId(),
				passwordEncoder.encode(userDiscord.getEmail()),
				userDiscord.getAvatar(),
				userDiscord.getVerified(),
				userDiscord.getEmail(),
				userDiscord.getUsername(),
				userDiscord.getFullUsername(),
				Arrays.asList(Role.ROLE_USER),
				Boolean.TRUE
		));
	}



	public Mono<UserMapperModel> ifRegister(com.ard333.springbootwebfluxjjwt.security.discord.model.User userDiscord) {
		return 	userRepository.save(new UserDomain(
				userDiscord.getId(),
				passwordEncoder.encode(userDiscord.getEmail()),
				userDiscord.getAvatar(),
				userDiscord.getVerified(),
				userDiscord.getEmail(),
				userDiscord.getUsername(),
				userDiscord.getFullUsername(),
				Arrays.asList(Role.ROLE_USER),
				Boolean.TRUE
		)).map((user) -> new UserMapperModel(
				userDiscord,
				Arrays.asList(Role.ROLE_USER),
				jwtUtil.generateToken(new User(
						userDiscord.getId()+","+userDiscord.getAvatar(),
						passwordEncoder.encode(userDiscord.getEmail()),
						Boolean.TRUE,
						Arrays.asList(Role.ROLE_USER)
				))));
	}
	public Mono<UserMapperModel> publicadorUserMapperModel(com.ard333.springbootwebfluxjjwt.security.discord.model.User userDiscord,
														   String token, List<Role> role) {
		return Mono.just(new UserMapperModel(userDiscord, role,token));
	}


	public Mono<ResponseEntity<?>> getTokenAndUsername( com.ard333.springbootwebfluxjjwt.security.discord.model.User userDiscord) {
		return TokenFindByUsername(userDiscord).map((userDetails) -> {
			if (passwordEncoder.encode(userDiscord.getEmail()).equals(userDetails.getPassword())) {
				return ResponseEntity.ok(publicadorUserMapperModel(userDiscord,jwtUtil.generateToken(userDetails),userDetails.getRoles()));
			} else {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			}
		}).defaultIfEmpty(new ResponseEntity<>(ifRegister(userDiscord),HttpStatus.CREATED));
	}

	public Mono<UserDomain> findByUsernameDomain(String id) {
		return userRepository.findByUsername(id);
	}
	public Flux<UserDomain> getUSer() {
		return userRepository.findAll();
	}
}
