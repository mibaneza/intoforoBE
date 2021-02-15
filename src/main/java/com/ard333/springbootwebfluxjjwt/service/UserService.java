package com.ard333.springbootwebfluxjjwt.service;


import com.ard333.springbootwebfluxjjwt.domain.UserDomain;
import com.ard333.springbootwebfluxjjwt.model.Duall;
import com.ard333.springbootwebfluxjjwt.model.User;
import com.ard333.springbootwebfluxjjwt.model.UserInfo;
import com.ard333.springbootwebfluxjjwt.repository.UserRepository;
import com.ard333.springbootwebfluxjjwt.security.JWTUtil;
import com.ard333.springbootwebfluxjjwt.security.PBKDF2Encoder;
import com.ard333.springbootwebfluxjjwt.security.model.Role;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.ard333.springbootwebfluxjjwt.security.model.UserMapperModel;
import com.ard333.springbootwebfluxjjwt.service.util.Getdate;
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
	Getdate getdate;
	@Autowired
	private JWTUtil jwtUtil;

	@Autowired
	private PBKDF2Encoder passwordEncoder;

	@Autowired
	private UserRepository userRepository;

	public Date datByUser(String id) {
		return userRepository.findByUsername(id).map( (u) -> {
			return u.getIndate();

		}).block();
	}
	public Mono<UserDomain> findid(String id){
		return userRepository.findByUsername(id);
	}
	public Mono<UserInfo> findUsername(String id){
		return userRepository.findByUsername(id)
				.map((userDomain) -> new UserInfo(userDomain,getdate.converter(userDomain.getIndate())));
	}
	public Mono<User> TokenFindByUsername(com.ard333.springbootwebfluxjjwt.security.discord.model.User userDiscord) {
		return userRepository.findByUsername(userDiscord.getId())
				.map((us) -> {
					us.setAvatar(userDiscord.getAvatar());
					return us;
				})
				.flatMap(userRepository::save)
				.map((user) ->  new User(user.getUsername()+","+userDiscord.getAvatar()+","+userDiscord.getUsername()+","+user.getRoles().toArray()[0],
						user.getPassword(),user.getEnabled(),user.getRoles()));
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
				Boolean.TRUE,
				getdate.date(),
				(long) 0,
				(long) 0
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
				Boolean.TRUE,
				getdate.date(),
				(long) 0,
				(long) 0
		)).map((user) -> new UserMapperModel(
				userDiscord,
				Arrays.asList(Role.ROLE_USER),
				jwtUtil.generateToken(new User(
						userDiscord.getId()+","+userDiscord.getAvatar()+","+userDiscord.getUsername()+","+Role.ROLE_USER,
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

	public Mono<UserDomain> findByUsernameUpdateComentPost(Duall duall) {
		return userRepository.findByUsername(duall.getDualv1())
				.map((us) -> {
					switch (duall.getDualv2()){
						case "comment":
							us.setQuantitycomment(us.getQuantitycomment()+1);
							return us;
						case "post":
							us.setQuantitypost(us.getQuantitypost()+1);
							return us;
						default:
							us.setQuantitycomment(us.getQuantitycomment()+1);
							us.setQuantitypost(us.getQuantitypost()+1);
							return us;
					}
				})
				.flatMap(userRepository::save)
				;
	}







}
