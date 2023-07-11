package com.example.authinjitsi.model;

public enum Affiliation {
    MEMBER("MEMBER"),
    OWNER("OWNER");
    final String affiliation;

    Affiliation(String affiliation) {
        this.affiliation = affiliation;
    }
}
