package com.supplier.config.auth.token;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class Token {
    private final String token;
    private final LocalDateTime expires;

    public Token(String token, long expires) {
        this.token = token;
        this.expires = LocalDateTime.ofInstant(new Date(expires).toInstant(), ZoneId.systemDefault());
    }

    public String getToken() {
        return token;
    }

    public LocalDateTime getExpires() {
        return expires;
    }
}