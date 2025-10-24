// src/test/java/com/example/biblioteca/repositories/UserRepositoryTest.java

package com.example.biblioteca;

// ... (imports)

import com.example.biblioteca.entities.User;
import com.example.biblioteca.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase; // NOVO IMPORT
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace; // NOVO IMPORT
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.ANY) // <--- ADICIONE ESTA LINHA
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void shouldSaveAndFindUserById() {
        // 1. Arrange (Preparar)
        User user = new User();
        user.setName("Lucas Teste");
        user.setEmail("lucas.teste@biblioteca.com");

        // 2. Act (Executar)
        User savedUser = userRepository.save(user); // Salva no banco de dados
        User foundUser = userRepository.findById(savedUser.getId()).orElse(null);

        // 3. Assert (Verificar)
        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getId()).isNotNull();
        assertThat(foundUser).isEqualTo(savedUser);
        assertThat(foundUser.getName()).isEqualTo("Lucas Teste");
    }

    @Test
    void shouldFindUserByEmail() {
        // 1. Arrange
        String testEmail = "unique.email@teste.com";
        User user = new User();
        user.setName("Email Finder");
        user.setEmail(testEmail);
        userRepository.save(user);

        // 2. Act
        User foundUser = userRepository.findByEmail(testEmail);

        // 3. Assert
        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getEmail()).isEqualTo(testEmail);
    }

    @Test
    void shouldDeleteUser() {
        // 1. Arrange
        User user = new User();
        user.setName("User to Delete");
        user.setEmail("delete@me.com");
        User savedUser = userRepository.save(user);

        // 2. Act
        userRepository.deleteById(savedUser.getId());

        // 3. Assert
        // Tenta buscar o usuário deletado e verifica se ele está vazio
        assertThat(userRepository.findById(savedUser.getId())).isEmpty();
    }
}