package com.example.biblioteca.controllers;

import com.example.biblioteca.entities.User;
import com.example.biblioteca.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*") // Permite requisições do Android
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

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrar(@RequestBody User user) {
        try {
            // Define valor padrão para 'type' caso não tenha sido enviado
            if (user.getType() == null || user.getType().isEmpty()) {
                user.setType("n"); // você pode colocar "USER" ou outro valor que faça sentido
            }

            User usuarioSalvo = userService.cadastrarUsuario(user);

            return ResponseEntity.ok(usuarioSalvo);

        } catch (Exception e) {
            // Retorna mensagem de erro detalhada
            return ResponseEntity.status(500).body("Erro ao cadastrar usuário: " + e.getMessage());
        }
    }
}
