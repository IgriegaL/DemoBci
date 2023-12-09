package com.example.demoBci.Service;

import com.example.demoBci.Entity.User;
import com.example.demoBci.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository usuarioRepository;

    public UserService(UserRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public boolean correoExistente(String correo) {
        return usuarioRepository.existsByCorreo(correo);
    }

    public User registrarUsuario(User usuario) {
        return usuarioRepository.save(usuario);
    }
    public List<User> obtenerTodosUsuarios() {
        return usuarioRepository.findAll();
    }
}