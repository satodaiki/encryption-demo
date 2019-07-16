package com.satodai.demo.encryptiondemo.domain.model;

import lombok.Data;

/**
 * 暗号化情報を含むJWTのオブジェクト
 */
@Data
public class EncryptJwt {

    private Jwt jwt;

    private String jwtStr;
}
