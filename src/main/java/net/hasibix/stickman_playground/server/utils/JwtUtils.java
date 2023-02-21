package net.hasibix.stickman_playground.server.utils;

import java.security.Key;
import io.jsonwebtoken.Jwts;

public class JwtUtils {
    public static String createToken(Key secKey, String payload) {
        return Jwts.builder()
            .signWith(secKey)
            .setPayload(payload)
            .compact();
    }
}
