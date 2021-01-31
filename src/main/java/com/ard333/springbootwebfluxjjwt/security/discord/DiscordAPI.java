package com.ard333.springbootwebfluxjjwt.security.discord;


import com.ard333.springbootwebfluxjjwt.security.discord.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.io.InputStream;

@Slf4j
@RequiredArgsConstructor
public class DiscordAPI
{
    public static final String BASE_URI = "https://discord.com/api";
    private static final Gson gson = new GsonBuilder().serializeNulls().enableComplexMapKeySerialization().create();
    private final String accessToken;

    private static <T> T toObject(String str, Class<T> clazz)
    {
        return gson.fromJson(str, clazz);
    }

    private String getVersion() throws IOException
    {
        InputStream resourceAsStream = this.getClass().getResourceAsStream("/version.properties");
        java.util.Properties prop = new java.util.Properties();
        prop.load(resourceAsStream);
        return prop.getProperty("version");
    }

    private void setHeaders(org.jsoup.Connection request) throws IOException
    {
        request.header("Authorization", "Bearer " + accessToken);
        request.header("User-Agent",
                String.format("Mokulu-Discord-OAuth2-Java, version, platform",  System.getProperty("os.name"),
                        System.getProperty("os.version")));
    }

    private String handleGet(String path) throws IOException
    {
        org.jsoup.Connection request = Jsoup.connect(BASE_URI + path).ignoreContentType(true);
        setHeaders(request);

        return request.get().body().text();
    }

    public User fetchUser() throws IOException
    {
        return toObject(handleGet("/users/@me"), User.class);
    }


}