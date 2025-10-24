package com.example.biblioteca.repositories;

// package do UserRepository


// Imports necessários
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.biblioteca.entities.User; // Importa a entidade User

// A interface UserRepository

public interface UserRepository extends JpaRepository<User, Long> {
    // Este método é criado automaticamente pelo Spring Data JPA
    // e permite buscar um usuário pelo email.
    User findByEmail(String email);
}
