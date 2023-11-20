package com.example.springrest.getapi;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Data
@Profile({"scrum"})
public class GetBO {
    @Value("${baseURI}")
    private String baseURI;
    @Value("${x-api-version}")
    private String xApiVersion;
    @Value("${channel-id}")
    private int channelId;
    @Value("${content-type}")
    private String contentType;
    @Value("${email}")
    private String email;
    @Value("${password}")
    private String password;
    @Value("${grantType}")
    private String grantType;
    @Value("${refreshToken}")
    private String refreshToken;
}

