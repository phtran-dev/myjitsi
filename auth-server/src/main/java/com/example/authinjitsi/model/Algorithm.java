package com.example.authinjitsi.model;

public enum Algorithm {
    HS256("HS256"),
    HS512("HS512");
    final String algorithm;

    Algorithm(String algorithm) {
        this.algorithm = algorithm;
    }
}
