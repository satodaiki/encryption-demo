package com.satodai.demo.encryptiondemo.util;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import org.springframework.stereotype.Component;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.text.ParseException;

/**
 * RSA暗号のユーティリティクラス
 */
@Component
public class RSAEncryptUtil {

    /**
     * RSA暗号化アルゴリズムを使用した署名の作成
     *
     * @param headerJson
     * @param payloadJson
     * @return 署名付きJWSオブジェクト
     */
    public JWSObject makeSignature(String headerJson, String payloadJson, RSAPrivateKey privateKey) throws JOSEException, ParseException {

        JWSObject jwsObject = new JWSObject(
                JWSHeader.parse(headerJson),
                new Payload(payloadJson));

        // Create RSA-signer with the private key
        JWSSigner signer = new RSASSASigner(privateKey);
        jwsObject.sign(signer);

        return jwsObject;
    }

    /**
     * RSA暗号化アルゴリズムを使用した署名の作成
     *
     * @param headerJson
     * @param payloadJson
     * @return 署名付きJWT文字列
     */
    public String makeSignatureStr(String headerJson, String payloadJson, RSAPrivateKey privateKey) throws JOSEException, ParseException {
        return makeSignature(headerJson, payloadJson, privateKey).serialize();
    }

    /**
     * RSA暗号化アルゴリズムを使用して作成された署名の検証
     *
     * @param jwt
     * @param publicKey
     * @return 検証結果
     */
    public boolean validSignature(String jwt, RSAPublicKey publicKey) throws JOSEException, ParseException {
        JWSObject decodeObject = JWSObject.parse(jwt);

        JWSVerifier verifier = new RSASSAVerifier(publicKey);
        return decodeObject.verify(verifier);
    }
}
