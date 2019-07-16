package com.satodai.demo.encryptiondemo.controller;

import com.nimbusds.jose.JOSEException;
import com.satodai.demo.encryptiondemo.domain.form.JwtForm;
import com.satodai.demo.encryptiondemo.domain.model.Jwt;
import com.satodai.demo.encryptiondemo.domain.model.RsaEncryptJwt;
import com.satodai.demo.encryptiondemo.domain.service.EncryptService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.ParseException;

@Controller
public class EncryptController {

    @Autowired
    EncryptService encryptService;

    @GetMapping
    public String getIndex(@ModelAttribute JwtForm form) {
        return "encrypt";
    }

    @PostMapping("encrypt")
    public String postEncrypt(@ModelAttribute JwtForm form, Model model)
            throws ParseException, NoSuchAlgorithmException, JOSEException {

        Jwt jwt = new Jwt();
        BeanUtils.copyProperties(form, jwt);

        // 署名付与処理
        RsaEncryptJwt encryptJwt = encryptService.makeRsaEncryptJwt(jwt);

        model.addAttribute("rsaEncryptJwt", encryptJwt);

        return "encrypt";
    }

    @PostMapping("decrypt")
    public String postDecrypt(@ModelAttribute RsaEncryptJwt form, Model model)
            throws InvalidKeySpecException, NoSuchAlgorithmException, ParseException, JOSEException {

        boolean result = encryptService.validRsaEncryptJwt(form);

        model.addAttribute("signatureVerify", result);
        model.addAttribute("jwtForm", form.getJwt());

        return "encrypt";
    }

/*
    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Exception e, Model model) {
        model.addAttribute("error", "内部サーバーエラー: GlobalControllAdvice");

        model.addAttribute("message", "Exceptionが発生しました");

        model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);

        return "error";
    }
*/
}
