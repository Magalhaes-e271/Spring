package com.example.biblioteca.controllers;

import com.example.biblioteca.entities.User;
import com.example.biblioteca.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*") // Permite Android
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user) {
        Optional<User> usuarioAutenticado = userService.autenticar(user.getEmail(), user.getSenha());
        return usuarioAutenticado.map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(401).build());
    }
}
