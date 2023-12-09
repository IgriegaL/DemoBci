package com.example.demoBci.Security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.io.Encoders;
import javax.crypto.SecretKey;

import java.util.logging.Logger;


import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    //Opcional para usar llave
    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private int jwtExpiration;

    public String generateToken(String username) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpiration);

        //Generating a safe HS256 Secret key
        SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
        String secretString = Encoders.BASE64.encode(key.getEncoded());

        // Create a Logger para ver llave desde consola
        Logger logger = Logger.getLogger(secretString);
        logger.info("Secret key: " + secretString);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512,secretString)
                .compact();
    }

    //Opcional para usar llave de el cfg
    private Key getkey() {
        byte[] keyBytes= Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
