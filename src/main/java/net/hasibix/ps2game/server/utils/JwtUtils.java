package net.hasibix.ps2game.server.utils;

import java.security.Key;
import io.jsonwebtoken.Jwts;

public class JwtUtils {
    public static String CreateToken(Key secKey, String payload) {
        return Jwts.builder()
            .signWith(secKey)
            .setPayload(payload)
            .compact();
    }
}
