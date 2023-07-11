package com.example.authinjitsi.model;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
public class Request {
//    @NotNull(message = "Algorithm must not be null")
//    private Algorithm alg;
//    @NotBlank(message = "Secret must not be null")
//    private String secret;
//    @NotBlank(message = "Issuer must not be null")
//    private String iss;
//    @NotBlank(message = "Audiences must not be null")
//    private String aud;
//    private String sub;
//    private String room;
//    private long nbf;
//    private long exp;
//    private String ctxUsrId;
    @NotBlank
    private String ctxUsrName;
    @NotBlank
    private String ctxUsrEmail;
    private String ctxUsrAvatar;
//    private Affiliation ctxUsrAffiliation;
//    private boolean ctxUsrLobbyByPass;
//    private boolean ctxFtRecording;
//    private boolean ctxFtLiveStreaming;
//    private boolean ctxFtScreenSharing;
//    private boolean ctxFtSipOutboundCall;
}
