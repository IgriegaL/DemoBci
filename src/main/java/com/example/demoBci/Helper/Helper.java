package com.example.demoBci.Helper;

import com.example.demoBci.Entity.User;
import com.example.demoBci.Entity.UserRequest;
import com.example.demoBci.Security.JwtTokenProvider;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
public class Helper {

    private final JwtTokenProvider jwtTokenProvider;

    public Helper(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }


    public User HelperUser (UserRequest usuario) {

        // Generar token JWT
        String token = jwtTokenProvider.generateToken(usuario.getEmail());

        // fecha
        Date now = new Date();

        User BuildToUser = User.builder()
                .token(token)
                .correo(usuario.getEmail())
                .nombre(usuario.getName())
                .password(usuario.getPassword())
                .phones(usuario.toPhoneList())
                .created(now)
                .modified(now)
                .lastLogin(now)
                .isActive(true).build();
        return BuildToUser;
    }
}
