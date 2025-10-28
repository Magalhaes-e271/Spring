package com.example.biblioteca.service;

import com.example.biblioteca.entities.Book;
import com.example.biblioteca.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    // Injeção de dependência via construtor
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // ✅ Listar todos os livros
    public List<Book> listarTodos() {
        return bookRepository.findAll();
    }

    // ✅ Buscar livro por ID
    public Optional<Book> buscarPorId(Long id) {
        return bookRepository.findById(id);
    }

    // ✅ Cadastrar novo livro
    public Book cadastrarLivro(Book book) {
        return bookRepository.save(book);
    }

    // ✅ Atualizar livro existente
    public Book atualizarLivro(Long id, Book novoLivro) {
        return bookRepository.findById(id)
                .map(livroExistente -> {
                    livroExistente.setTitulo(novoLivro.getTitulo());
                    livroExistente.setAutor(novoLivro.getAutor());
                    livroExistente.setDescricao(novoLivro.getDescricao());
                    livroExistente.setImg(novoLivro.getImg());
                    livroExistente.setType(novoLivro.getType());
                    livroExistente.setQuantia(novoLivro.getQuantia());
                    return bookRepository.save(livroExistente);
                })
                .orElseThrow(() -> new RuntimeException("Livro não encontrado com ID: " + id));
    }

    // ✅ Deletar livro
    public void deletarLivro(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new RuntimeException("Livro não encontrado com ID: " + id);
        }
        bookRepository.deleteById(id);
    }
}
