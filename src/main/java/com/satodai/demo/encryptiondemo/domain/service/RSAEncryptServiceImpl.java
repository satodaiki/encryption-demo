package com.satodai.demo.encryptiondemo.domain.service;

import com.satodai.demo.encryptiondemo.domain.model.Jwt;
import com.satodai.demo.encryptiondemo.domain.model.RsaEncryptJwt;
import org.springframework.stereotype.Service;

@Service("RSAEncryptServiceImpl")
public class RSAEncryptServiceImpl implements EncryptService {

    @Override
    public RsaEncryptJwt serialize(Jwt jwt) {

        RsaEncryptJwt encryptJwt = new RsaEncryptJwt();

        jwt.setSignature("test");

        encryptJwt.setJwt(jwt);
        encryptJwt.setJwtStr("test");
        encryptJwt.setPrivateKey("test");
        encryptJwt.setPublicKey("test");

        return encryptJwt;
    }
}
