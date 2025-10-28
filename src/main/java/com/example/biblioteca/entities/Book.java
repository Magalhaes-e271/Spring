package com.example.biblioteca.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "livros") // nome da tabela no banco
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String autor;

    @Column(length = 1000) // aumenta limite de caracteres
    private String descricao;

    private String img; // URL ou caminho da imagem

    private String type; // Ex: "ROMANCE", "TÉCNICO", "PADRAO"

    private Integer quantia;

    // 🔹 Construtor padrão (obrigatório pro JPA)
    public Book() {
    }

    // 🔹 Construtor completo (opcional)
    public Book(Long id, String titulo, String autor, String descricao, String img, String type, Integer quantia) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.descricao = descricao;
        this.img = img;
        this.type = type;
        this.quantia = quantia;
    }

    // 🔹 Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getQuantia() {
        return quantia;
    }
    public void setQuantia(Integer quantia) {
        this.quantia = quantia;
    }
}
