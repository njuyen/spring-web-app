package com.supplier.config.auth.token;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.util.StringUtils;

public class TokenProvider {

    private final String secretKey;

    private final int tokenValidity;

    public TokenProvider(String secretKey, int tokenValidity) {
        this.secretKey = secretKey;
        this.tokenValidity = tokenValidity;
    }

    // Token format = username:expiretime:hashcode
    public Token createToken(UserDetails userDetails) {
        long expires = System.currentTimeMillis() + tokenValidity * 1000L;
        String token = userDetails.getUsername() + ":" + expires + ":" + computeSignature(userDetails, expires);
        return new Token(token, expires);
    }

    public String getUsernameFromToken(String authToken) {
        if (StringUtils.isEmpty(authToken)) {
            return null;
        }
        String[] parts = authToken.split(":");
        return parts[0];
    }

    public boolean validateToken(String authToken, UserDetails userDetails) {
        String[] parts = authToken.split(":");
        if (parts.length != 3) {
            return false;
        }
        long expires;
        try {
            expires = Long.parseLong(parts[1]);
        } catch (NumberFormatException ex) {
            return false;
        }
        String signature = parts[2];
        String expectedSignature = computeSignature(userDetails, expires);
        return expires >= System.currentTimeMillis() && signature.equals(expectedSignature);
    }

    private String computeSignature(UserDetails userDetails, long expires) {
        String signature = userDetails.getUsername() + ":" + expires + ":" + userDetails.getPassword() + ":"
                + secretKey;
        MessageDigest messageDigest;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("No MD5 algorithm available!");
        }
        return new String(Hex.encode(messageDigest.digest(signature.getBytes())));
    }
}
