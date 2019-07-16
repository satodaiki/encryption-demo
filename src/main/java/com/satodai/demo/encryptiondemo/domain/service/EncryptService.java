package com.satodai.demo.encryptiondemo.domain.service;

import com.satodai.demo.encryptiondemo.domain.model.EncryptJwt;
import com.satodai.demo.encryptiondemo.domain.model.Jwt;

public interface EncryptService<E extends EncryptJwt> {

    E serialize(Jwt jwt);
}
