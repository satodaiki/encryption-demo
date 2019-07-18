package com.satodai.demo.encryptiondemo.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("openid.google")
@Data
public class OpenIdConnectGoogleProperties {

    private String clientId;
    private String clientSecret;
    private String scope;
    private String authorizationUri;
    private String tokenUri;
    private String userinfoUri;
    private String redirectUri;
}
