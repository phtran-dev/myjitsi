package com.example.authinjitsi.service;

import com.example.authinjitsi.model.Request;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class GenerateTokenServiceTest {
    @Autowired
    GenerateTokenService generateTokenService;
    Request userInfo;
    @BeforeEach
    void init(){
        userInfo = Request.builder()
                .ctxUsrName("test")
                .ctxUsrEmail("test")
                .ctxUsrAvatar("abc.xyz.com")
                .build();
    }
    @Test
    void test(){
        final String token = this.generateTokenService.generateToken(userInfo);
        log.info("{}",token);
    }
}