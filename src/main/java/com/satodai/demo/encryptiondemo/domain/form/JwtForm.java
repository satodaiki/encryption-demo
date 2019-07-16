package com.satodai.demo.encryptiondemo.domain.form;

import lombok.Data;

/**
 * JWTフォームクラス
 */
@Data
public class JwtForm {

    /** ヘッダー部 */
    private String header;

    /** ペイロード部 */
    private String payload;

    /** 署名部 */
    private String signature;
}
