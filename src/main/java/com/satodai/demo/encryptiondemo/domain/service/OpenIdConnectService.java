package com.satodai.demo.encryptiondemo.domain.service;

import com.nimbusds.oauth2.sdk.AuthorizationRequest;
import com.nimbusds.oauth2.sdk.ResponseType;
import com.nimbusds.oauth2.sdk.Scope;
import com.nimbusds.oauth2.sdk.id.ClientID;
import com.nimbusds.oauth2.sdk.id.State;
import com.satodai.demo.encryptiondemo.properties.OpenIdConnectGoogleProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;

@Service
public class OpenIdConnectService {

    @Autowired
    OpenIdConnectGoogleProperties openIdConnectGoogleProperties;

    public URI makeAuthorizationRequest() throws URISyntaxException {

        // The authorisation endpoint of the server
        URI authzEndpoint = new URI(openIdConnectGoogleProperties.getAuthorizationUri());

        // The client identifier provisioned by the server
        ClientID clientID = new ClientID(openIdConnectGoogleProperties.getClientId());

        // The requested scope values for the token
        Scope scope = new Scope(openIdConnectGoogleProperties.getScope());

        // The client callback URI, typically pre-registered with the server
        URI callback = new URI(openIdConnectGoogleProperties.getRedirectUri());

        // Generate random state string for pairing the response to the request
        State state = new State();

        // Build the request
        AuthorizationRequest request = new AuthorizationRequest.Builder(
                new ResponseType(ResponseType.Value.CODE), clientID)
                .scope(scope)
                .state(state)
                .redirectionURI(callback)
                .endpointURI(authzEndpoint)
                .build();

        // Use this URI to send the end-user's browser to the server
        URI requestURI = request.toURI();

        return requestURI;
    }
}
