package com.example.authinjitsi.controller;

import com.example.authinjitsi.model.Request;
import com.example.authinjitsi.service.IGenerateTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/jitsi")
@CrossOrigin("*")
public class JitsiController {
    private final IGenerateTokenService generateTokenService;

    @PostMapping
    public String getTokenBased(@Valid @RequestBody Request user) {
        return this.generateTokenService.generateToken(user);
    }
    @PostMapping("/link")
    public String getLink(@Valid @RequestBody Request user) {
        return this.generateTokenService.generateLinkWithParams(user);
    }
}
