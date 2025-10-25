package com.example.biblioteca.controllers;

import com.example.biblioteca.entities.User;
import com.example.biblioteca.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<User> cadastrar(@RequestBody User usuario) {
        User novoUsuario = userService.cadastrarUsuario(usuario);
        return ResponseEntity.ok(novoUsuario);
    }

    @GetMapping
    public ResponseEntity<List<User>> listarUsuarios() {
        return ResponseEntity.ok(userService.listarTodosUsuarios());
    }
}
