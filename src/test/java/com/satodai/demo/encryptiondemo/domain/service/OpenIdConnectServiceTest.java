package com.satodai.demo.encryptiondemo.domain.service;

import com.satodai.demo.encryptiondemo.properties.OpenIdConnectGoogleProperties;
import mockit.Expectations;
import mockit.Injectable;
import mockit.Mocked;
import mockit.Tested;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.plaf.nimbus.State;
import java.net.URI;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
public class OpenIdConnectServiceTest {

    @Tested
    private OpenIdConnectService openIdConnectService;

    @Injectable
    OpenIdConnectGoogleProperties openIdConnectGoogleProperties;

    @Test
    public void リクエストの発行(@Mocked State state) throws Exception {

        new Expectations() {{
            openIdConnectGoogleProperties.getAuthorizationUri();
            result = "https://accounts.google.com/o/oauth2/v2/auth";
        }};
        new Expectations() {{
            openIdConnectGoogleProperties.getClientId();
            result = "35577756689-mcts68k50dtr587hmsnh01f0m5ab0k0d.apps.googleusercontent.com";
        }};
        new Expectations() {{
            openIdConnectGoogleProperties.getScope();
            result = "openid email profile https://www.googleapis.com/auth/calendar";
        }};
        new Expectations() {{
            openIdConnectGoogleProperties.getRedirectUri();
            result = "http://localhost:8080/openid/redirect";
        }};
        new Expectations() {{

        }};

        URI uri = openIdConnectService.makeAuthorizationRequest();

        assertThat(uri.toString(), is("https://accounts.google.com/o/oauth2/v2/auth?response_type=code&redirect_uri=http%3A%2F%2Flocalhost%3A8080%2Fopenid%2Fredirect&state=AANhiowF9bc46fVw1rNzFhR-APAGr5-q4hGbQQmCvwk&client_id=35577756689-mcts68k50dtr587hmsnh01f0m5ab0k0d.apps.googleusercontent.com&scope=openid+email+profile+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fcalendar"));
    }

}
