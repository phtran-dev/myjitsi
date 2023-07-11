package com.example.authinjitsi.service;

import com.example.authinjitsi.model.Request;

public interface IGenerateTokenService {
    String generateToken(Request user);

    String generateLinkWithParams(Request user);
}
