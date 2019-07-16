package com.satodai.demo.encryptiondemo.domain.service;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSObject;
import com.satodai.demo.encryptiondemo.domain.model.Jwt;
import com.satodai.demo.encryptiondemo.domain.model.RsaEncryptJwt;
import com.satodai.demo.encryptiondemo.util.RSAEncryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.text.ParseException;

@Service
public class EncryptService {

    @Autowired
    RSAEncryptUtil rsaEncryptUtil;

    /** RSA暗号に使用するキーサイズ */
    private final static int RSA_KEY_SIZE = 2048;

    /**
     * RSA暗号を使用したJWTオブジェクトの生成
     *
     * @param jwt
     * @return
     */
    public RsaEncryptJwt makeRsaEncryptJwt(Jwt jwt) throws NoSuchAlgorithmException, ParseException, JOSEException {

        RsaEncryptJwt encryptJwt = new RsaEncryptJwt();

        // キーペアの生成とJWTオブジェクトへの格納
        KeyPairGenerator keyGenerator = KeyPairGenerator.getInstance("RSA");
        keyGenerator.initialize(RSA_KEY_SIZE);

        KeyPair kp = keyGenerator.genKeyPair();
        RSAPublicKey publicKey = (RSAPublicKey) kp.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) kp.getPrivate();

        // 16進数文字列に変換し格納
        encryptJwt.setPublicKey(DatatypeConverter.printHexBinary(publicKey.getEncoded()).toLowerCase());
        encryptJwt.setPrivateKey(DatatypeConverter.printHexBinary(privateKey.getEncoded()).toLowerCase());

        // 署名付与とJWTオブジェクトへの格納
        JWSObject jwsObject = rsaEncryptUtil.makeSignature(jwt.getHeader(), jwt.getPayload(), privateKey);
        jwt.setSignature(jwsObject.getSignature().toString());
        encryptJwt.setJwt(jwt);
        encryptJwt.setJwtStr(jwsObject.serialize());

        return encryptJwt;
    }

    public boolean validRsaEncryptJwt(RsaEncryptJwt rsaEncryptJwt)
            throws NoSuchAlgorithmException, InvalidKeySpecException, ParseException, JOSEException {

        // 公開鍵を16進数文字列からbyte配列へ変換
        byte[] publicKeyBytes = DatatypeConverter.parseHexBinary(rsaEncryptJwt.getPublicKey());

        // PrivateKeyへ変換
        // https://codeday.me/jp/qa/20190215/265026.html
        KeyFactory kf = KeyFactory.getInstance("RSA");
        RSAPublicKey publicKey = (RSAPublicKey) kf.generatePublic(new X509EncodedKeySpec(publicKeyBytes));

        // 署名検証の実施
        boolean result = rsaEncryptUtil.validSignature(rsaEncryptJwt.getJwtStr(), publicKey);

        JWSObject jwsObject = JWSObject.parse(rsaEncryptJwt.getJwtStr());

        Jwt jwt = new Jwt();
        jwt.setHeader(jwsObject.getHeader().toJSONObject().toJSONString());
        jwt.setPayload(jwsObject.getPayload().toJSONObject().toJSONString());
        jwt.setSignature(jwsObject.getSignature().decodeToString());

        rsaEncryptJwt.setJwt(jwt);

        return result;
    }
}
