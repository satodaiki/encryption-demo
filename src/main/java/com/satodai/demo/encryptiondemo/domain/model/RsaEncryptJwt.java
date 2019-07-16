package com.satodai.demo.encryptiondemo.domain.model;

import lombok.Data;

@Data
public class RsaEncryptJwt extends EncryptJwt {

    /** 公開鍵の16進数文字列 */
    private String publicKey;

    /** 秘密鍵の16進数文字列 */
    private String privateKey;
}
