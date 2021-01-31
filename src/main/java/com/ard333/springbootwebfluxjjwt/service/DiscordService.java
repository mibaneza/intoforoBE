package com.ard333.springbootwebfluxjjwt.service;


import com.ard333.springbootwebfluxjjwt.security.discord.DiscordAPI;
import com.ard333.springbootwebfluxjjwt.security.discord.DiscordOAuth;
/*import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.requests.RestAction;*/
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.List;

@Component
public class DiscordService {

    @Value("${foro.discord.clientID}")
    private String clientID;
    @Value("${foro.discord.clientSecret}")
    private String clientSecret;

    @Value("${foro.discord.BOT}")
    private String botSecret;

    private static final Logger LOGGER = LoggerFactory.getLogger(DiscordService.class);
    private static final String ERROR = "NOT_FOUND";
    private static final String CODE = "SNOT-404-1";
    private static final  String scope[] = {"indentify"};
    private  com.ard333.springbootwebfluxjjwt.security.discord.model.User user;
    public  com.ard333.springbootwebfluxjjwt.security.discord.model.User getUserDiscord(String accessToken) {
        DiscordOAuth oauthHandler = new DiscordOAuth(clientID, clientSecret,  scope);
        DiscordAPI api = new DiscordAPI(accessToken);
        try {
            user = api.fetchUser();
        }catch (IOException e){
            LOGGER.error(ERROR, e);
        }
        return  user ;
    }
    /*
    public ResponseEntity< List<net.dv8tion.jda.api.entities.Role>> retrieveUserById() throws Exception{


        JDA jda = JDABuilder.createDefault(botSecret).build();




        System.out.println( jda.getRoleById("668183625166094378"));
        List<Role> roles = jda.getRolesByName("admin", true);
        System.out.println(jda.getRoles());

        jda.getGuildById(712756519804076103L);
        Role role = jda.getRoleById(486290136267489300L);
        System.out.println(role);


                jda.retrieveUserById(77619273077366784L).map(User::getName)
                .queue(name -> {
                    // use name here
                    System.out.println("-------------------------");
                    System.out.println("-------------------------");
                    System.out.println(name);
                });
            return new ResponseEntity<>( jda.getRoles(),HttpStatus.CREATED);


    }*/

}