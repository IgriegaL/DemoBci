package com.example.demoBci.Controller;

import com.example.demoBci.Entity.User;
import com.example.demoBci.Entity.UserRequest;
import com.example.demoBci.Helper.Helper;
import com.example.demoBci.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping("/api/usuarios")
public class UserController {

    @Value("${Key.EmailPattern}")
    private String EMAIL_PATTERN;
    private final UserService usuarioService;

    private final Helper helper;


    public UserController(UserService usuarioService, Helper helper) {
        this.usuarioService = usuarioService;
        this.helper = helper;
    }

    @PostMapping("/registro")
    public ResponseEntity<Object> registrarUsuario(@Valid @RequestBody UserRequest usuario, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            // Respuesta con los campos que faltan
            Map<String, String> errores = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errores.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
        }
        if (usuarioService.correoExistente(usuario.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El correo ya registrado");
        }
        // Valida correo con expresión regular
        if (!usuario.getEmail().matches(EMAIL_PATTERN)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Formato de correo incorrecto");
        }

        // Guarda usuario en la base de datos
        User registrado;
        try {
            registrado = usuarioService.registrarUsuario(helper.HelperUser(usuario));
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            errorResponse.put("StackTrace", e.getStackTrace());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
        // Devuelve respuesta con el usuario registrado y otros campos
        return ResponseEntity.status(HttpStatus.CREATED).body(registrado);
    }

    @GetMapping("/usuariosregistados")
    public ResponseEntity<Object> obtenerTodosUsuarios() {
        List<User> usuarios = usuarioService.obtenerTodosUsuarios();

        if (usuarios.isEmpty() ) {
            Map<String, Object> response = new HashMap<>();
            response.put("200", "No hay usuarios registrados");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        // Devuelve usuarios si encuentra en la bd
        return ResponseEntity.status(HttpStatus.OK).body(usuarios);
    }
}
