package com.example.biblioteca.controllers;

import com.example.biblioteca.entities.Book;
import com.example.biblioteca.service.BookService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/livros")
@CrossOrigin(origins = "*") // Permite requisições do app Android
public class BookController {

    private final BookService bookService;

    @Value("${upload.dir:uploads}") // pasta padrão "uploads" se não houver config no application.properties
    private String uploadDir;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // ✅ Listar todos os livros
    @GetMapping
    public ResponseEntity<List<Book>> listarTodos() {
        List<Book> livros = bookService.listarTodos();
        return ResponseEntity.ok(livros);
    }

    // ✅ Buscar livro por ID
    @GetMapping("/{id}")
    public ResponseEntity<Book> buscarPorId(@PathVariable Long id) {
        Optional<Book> livro = bookService.buscarPorId(id);
        return livro.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ Cadastrar novo livro (com imagem)
    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody Book book) {
        try {
            if (book.getImg() == null || book.getImg().isEmpty()) {
                book.setImg("https://placehold.co/200x300");
            }

            if (book.getType() == null || book.getType().isEmpty()) {
                book.setType("ROMANCE");
            }

            Book novoLivro = bookService.cadastrarLivro(book);
            return ResponseEntity.ok(novoLivro);

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao cadastrar livro: " + e.getMessage());
        }
    }
    // ✅ Atualizar livro (com imagem opcional)
    @PutMapping(value = "/{id}", consumes = {"multipart/form-data"})
    public ResponseEntity<?> atualizar(
            @PathVariable Long id,
            @RequestPart("book") Book book,
            @RequestPart(value = "img", required = false) MultipartFile imagem
    ) {
        try {
            if (imagem != null && !imagem.isEmpty()) {
                String nomeArquivo = salvarImagem(imagem);
                book.setImg("/uploads/" + nomeArquivo);
            }

            Book atualizado = bookService.atualizarLivro(id, book);
            return ResponseEntity.ok(atualizado);

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao atualizar livro: " + e.getMessage());
        }
    }

    // ✅ Deletar livro
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        try {
            bookService.deletarLivro(id);
            return ResponseEntity.ok("Livro deletado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao deletar livro: " + e.getMessage());
        }
    }

    // 🧩 Método auxiliar para salvar imagem no servidor
    private String salvarImagem(MultipartFile imagem) throws IOException {
        File pastaUpload = new File(uploadDir);
        if (!pastaUpload.exists()) {
            pastaUpload.mkdirs();
        }

        String nomeArquivo = UUID.randomUUID() + "_" + imagem.getOriginalFilename();
        File destino = new File(pastaUpload, nomeArquivo);
        imagem.transferTo(destino);
        return nomeArquivo;
    }
}
