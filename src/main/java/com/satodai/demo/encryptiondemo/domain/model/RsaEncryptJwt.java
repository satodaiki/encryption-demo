package com.satodai.demo.encryptiondemo.domain.model;

import lombok.Data;

@Data
public class RsaEncryptJwt extends EncryptJwt {

    private String publicKey;

    private String privateKey;
}
