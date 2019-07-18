package com.satodai.demo.encryptiondemo.domain.service;


import com.nimbusds.oauth2.sdk.AuthorizationRequest;
import com.satodai.demo.encryptiondemo.properties.OpenIdConnectGoogleProperties;
import mockit.Injectable;
import mockit.Mocked;
import mockit.Tested;
import mockit.Verifications;
import org.apache.tomcat.util.http.parser.Authorization;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

public class OpenIdConnectServiceTest {

    @Tested
    private OpenIdConnectService openIdConnectService;

    @Injectable
    OpenIdConnectGoogleProperties openIdConnectGoogleProperties;

    @Mocked
    AuthorizationRequest.Builder authorizationBuilder;

    @Mocked
    AuthorizationRequest authorizationRequest;

    @Test
    public void リクエストの発行() throws Exception {

        new Verifications() {{
           authorizationBuilder.build();
           times = 1;
        }};
    }

}
