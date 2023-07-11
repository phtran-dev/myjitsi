package com.example.authinjitsi.service;

import com.example.authinjitsi.model.JwtBuilder;
import com.example.authinjitsi.model.Request;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class GenerateTokenService implements IGenerateTokenService{
    @Value("${jitsi.domain}")
    private String domain;

    @Override
    public String generateToken(Request user) {
        final String token = JwtBuilder.builder()
                .withDefaults()
                .withUserName(user.getCtxUsrName())
                .withUserEmail(user.getCtxUsrEmail())
                .withUserAvatar(user.getCtxUsrAvatar())
                .sign(user);
        log.info("Token: [{}]",token);
        return token;
    }
    @Override
    public String generateLinkWithParams(Request user) {
        final String token = JwtBuilder.builder()
                .withDefaults()
                .withUserName(user.getCtxUsrName())
                .withUserEmail(user.getCtxUsrEmail())
                .withUserAvatar(user.getCtxUsrAvatar())
                .sign(user);
        String roomName = UUID.randomUUID().toString();
        final String link = domain + roomName + "?jwt=" + token;
        log.info("Link: [{}]",link);
        return link;
    }
}
