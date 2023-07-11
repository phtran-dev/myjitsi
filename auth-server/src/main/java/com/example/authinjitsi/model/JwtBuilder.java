package com.example.authinjitsi.model;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@Component
public class JwtBuilder {
    private JWTCreator.Builder jwtBuilder;
    private Algorithm algorithm;
    private Map<String, Object> userClaims;
    private Map<String, Object> featureClaims;
    public static final long NBF_TIME_DELAY_SEC = 10;
    public static final long EXP_TIME_DELAY_SEC = 7200;

    private final String iss = "my_web_client";
    private final String aud = "my_server1";
    private final String secret = "my_jitsi_app_secret";

    private JwtBuilder() {
        userClaims = new HashMap<>();
        featureClaims = new HashMap<>();
        jwtBuilder = JWT.create();
    }

    /**
     * Creates a new JwtBuilder.
     * @return Returns a new builder that needs to be setup.
     */
    public static JwtBuilder builder() {
        return new JwtBuilder();
    }
    public JwtBuilder withUserId(String userId) {
        userClaims.put("id", userId != null ? userId : UUID.randomUUID().toString());
        return this;
    }

    /**
     * Set the value for user name
     * @param userName
     * @return
     */
    public JwtBuilder withUserName(String userName) {
        userClaims.put("name", userName);
        return this;
    }

    /**
     * set the value for url of image avatar
     * @param url
     * @return
     */
    public JwtBuilder withUserAvatar(String url) {
        userClaims.put("avatar", url);
        return this;
    }

    /**
     * Set the value for user email
     * @param email
     * @return
     */
    public JwtBuilder withUserEmail(String email) {
        userClaims.put("email", email);
        return this;
    }

    /**
     *
     * @param isLobbyByPass
     * @return
     */
    public JwtBuilder withUserLobbyByPass(boolean isLobbyByPass) {
        userClaims.put("lobby_bypass", isLobbyByPass);
        return this;
    }

    /**
     *
     * @param affiliation
     * @return
     */
    public JwtBuilder withUserAffiliation(Affiliation affiliation) {
        userClaims.put("affiliation", String.valueOf(affiliation).toLowerCase());
        return this;
    }

    /**
     *
     * @param isEnabled
     * @return
     */
    public JwtBuilder withLiveStreamingEnabled(boolean isEnabled) {
        featureClaims.put("livestreaming", isEnabled);
        return this;
    }

    /**
     * Sets the value for the recording feature claim. If value is true recording is enabled, false otherwise.
     * @param isEnabled
     * @return Returns a new builder with recording claim set.
     */
    public JwtBuilder withRecordingEnabled(boolean isEnabled) {
        featureClaims.put("recording", isEnabled);
        return this;
    }

    /**
     * Sets the value for the outbound feature claim. If value is true sip-outbound-call are enabled, false otherwise.
     * @param isEnabled
     * @return Returns a new builder with sip-outbound-call claim set.
     */
    public JwtBuilder withOutboundEnabled(boolean isEnabled) {
        featureClaims.put("sip-outbound-call", isEnabled);
        return this;
    }

    /**
     * Sets the value for the transcription feature claim. If value is true screen-sharing is enabled, false otherwise.
     * @param isEnabled
     * @return Returns a new builder with screen-sharing claim set.
     */
    public JwtBuilder withScreenSharingEnabled(boolean isEnabled) {
        featureClaims.put("screen-sharing", isEnabled);
        return this;
    }
    public JwtBuilder withExpTime(long expTime) {
        jwtBuilder.withClaim("exp", expTime);
        return this;
    }

    /**
     * Sets the value for the nbf claim.
     * @param nbfTime Unix timestamp is expected.
     * @return Returns a new builder with nbf claim set.
     */
    public JwtBuilder withNbfTime(long nbfTime) {
        jwtBuilder.withClaim("nbf", nbfTime);
        return this;
    }

    /**
     * @return Returns a new builder with iat claim set.
     */
    public JwtBuilder withIssueAt(long iatTime) {
        jwtBuilder.withClaim("iat",iatTime);
        return this;
    }

    /**
     * Sets the value for the room can join. If value is null -> will set to all (*), or otherwise.
     * @param room
     * @return Returns a new builder with room claim set.
     */
    public JwtBuilder withRoomName(String room) {
        jwtBuilder.withClaim("room", room);
        return this;
    }
    /**
     * Sets the value for the subject. If value is null -> will set to all (*), or otherwise.
     * @param sub
     * @return Returns a new builder with sub claim set.
     */
    public JwtBuilder withSubject(String sub) {
        jwtBuilder.withClaim("sub", sub);
        return this;
    }
    public JwtBuilder withDefaults() {
        return this.withExpTime(Instant.now().getEpochSecond() + EXP_TIME_DELAY_SEC) // Default value, no change needed.
                .withNbfTime(Instant.now().getEpochSecond() - NBF_TIME_DELAY_SEC) // Default value, no change needed.
                .withIssueAt(Instant.now().getEpochSecond())
                .withLiveStreamingEnabled(true)
                .withRecordingEnabled(true)
                .withOutboundEnabled(true)
                .withScreenSharingEnabled(true)
                .withUserAffiliation(Affiliation.OWNER)
                .withUserLobbyByPass(true)
                .withRoomName("*")
                .withSubject("*")
                ;
    }

    public String sign(Request userInfo){
//        if (userInfo.getAlg().equals(com.example.authinjitsi.model.Algorithm.HS512)){
//            algorithm = Algorithm.HMAC512(secret);
//        } else if (userInfo.getAlg().equals(com.example.authinjitsi.model.Algorithm.HS256)) {
//            algorithm = Algorithm.HMAC256(secret);
//        }
        algorithm = Algorithm.HMAC256(secret);
        Map<String, Object> context = new HashMap<>() {{
            put("user", userClaims);
            put("features", featureClaims);
        }};

        return jwtBuilder
                .withClaim("iss", iss)
                .withClaim("aud", aud)
                .withClaim("context", context)
                .sign(this.algorithm);
    }
}
