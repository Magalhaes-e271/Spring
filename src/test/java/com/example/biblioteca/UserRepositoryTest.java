package com.example.biblioteca;

import com.example.biblioteca.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.biblioteca.entities.*;
// Se estiver usando @DataJpaTest, importe-o no lugar de @SpringBootTest
// import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;

// Use @DataJpaTest se você quiser testar *apenas* a camada de banco de dados.
// É mais rápido e já configura o H2 automaticamente.
// @DataJpaTest
@SpringBootTest // Use @SpringBootTest se você já estava usando
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    // O teste que você já tinha (shouldSaveAndFindUserById) também serve!
    // Mas aqui está um que se parece mais com o seu "SELECT *":

    @Test
    void deveConectarEBuscarTodosOsUsuarios() {
        // 1. ARRANGE (Preparar)
        // Primeiro, vamos inserir um usuário para garantir que o SELECT não volte vazio
        User novoUsuario = new User();

        // Assumindo que você tem campos como 'nome' e 'email'
        // PREENCHA COM OS CAMPOS DA SUA ENTIDADE USER
        novoUsuario.setName("Usuario Teste");
        novoUsuario.setEmail("teste@email.com");
        // ... defina outros campos obrigatórios

        userRepository.save(novoUsuario);

        // 2. ACT (Agir)
        // Agora, vamos fazer o "SELECT * FROM usuarios"
        List<User> usuarios = userRepository.findAll();

        // 3. ASSERT (Verificar)
        // Imprime no console para você ver
        System.out.println("--- LISTA DE USUÁRIOS NO BANCO ---");
        for (User user : usuarios) {
            System.out.println("ID: " + user.getId() + ", Nome: " + user.getName());
        }
        System.out.println("------------------------------------");

        // Verifica se a lista não está vazia
        assertThat(usuarios).isNotEmpty();
    }
}