package com.example.biblioteca.controllers;
import com.example.biblioteca.entities.User;
import com.example.biblioteca.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository repo;

    // GET /users → lista todos os usuários
    @GetMapping
    public List<User> listAll() {
        return repo.findAll();
    }

    // GET /users/{id} → busca por id
    @GetMapping("/{id}")
    public User findOne(@PathVariable Long id) {
        return repo.findById(id).orElse(null);
    }

    // POST /users → cria novo usuário
    @PostMapping
    public User create(@RequestBody User user) {
        return repo.save(user);
    }

    // PUT /users/{id} → atualiza dados
    @PutMapping("/{id}")
    public User update(@PathVariable Long id, @RequestBody User updated) {
        User user = repo.findById(id).orElse(null);
        if(user != null) {
            user.setName(updated.getName());
            user.setEmail(updated.getEmail());
            user.setPassword(updated.getPassword());
            return repo.save(user);
        }
        return null;
    }

    // DELETE /users/{id} → remove usuário
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
}

