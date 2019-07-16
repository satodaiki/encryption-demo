package com.satodai.demo.encryptiondemo.controller;

import com.satodai.demo.encryptiondemo.domain.form.JwtForm;
import com.satodai.demo.encryptiondemo.domain.model.Jwt;
import com.satodai.demo.encryptiondemo.domain.model.RsaEncryptJwt;
import com.satodai.demo.encryptiondemo.domain.service.EncryptService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EncryptController {

    @Autowired
    @Qualifier("RSAEncryptServiceImpl")
    EncryptService<RsaEncryptJwt> encryptService;

    @GetMapping
    public String getIndex(@ModelAttribute JwtForm form) {
        return "encrypt";
    }

    @PostMapping("encrypt")
    public String postEncrypt(@ModelAttribute JwtForm form, Model model) {

        Jwt jwt = new Jwt();
        BeanUtils.copyProperties(form, jwt);
        RsaEncryptJwt encryptJwt = encryptService.serialize(jwt);

        model.addAttribute("rsaEncryptJwt", encryptJwt);

        return "encrypt";
    }
}
