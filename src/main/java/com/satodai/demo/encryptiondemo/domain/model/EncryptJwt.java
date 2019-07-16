package com.satodai.demo.encryptiondemo.domain.model;

import lombok.Data;

@Data
public class EncryptJwt {

    private Jwt jwt;

    private String jwtStr;
}
