package com.example.biblioteca.service;

import com.example.biblioteca.entities.User;
import com.example.biblioteca.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User cadastrarUsuario(User usuario) {
        return userRepository.save(usuario);
    }

    public Optional<User> autenticar(String email, String senha) {
        return userRepository.findByEmail(email)
                .filter(user -> user.getSenha().equals(senha));
    }
    public List<User> listarTodosUsuarios() {
        return userRepository.findAll();
    }
}
