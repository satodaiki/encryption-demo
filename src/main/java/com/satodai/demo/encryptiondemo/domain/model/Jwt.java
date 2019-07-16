package com.satodai.demo.encryptiondemo.domain.model;

import lombok.Data;

@Data
public class Jwt {

    private String header;

    private String payload;

    private String signature;
}
