package com.br.biblioteca.biblioteca.entity;

import com.br.biblioteca.biblioteca.dto.autor.RequestAutorDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "autores")
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String nacionalidade;
    @OneToMany(
            mappedBy = "autor",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonBackReference
    private List<Livro> livro = new ArrayList<>();

    public Autor() {
    }

    public Autor(RequestAutorDTO dto){
        this.nome = dto.nome();
        this.nacionalidade = dto.nacionalidade();
    }

    public Autor(String nome, String nacionalidade, List<Livro> livro, Long id) {
        this.nome = nome;
        this.nacionalidade = nacionalidade;
        this.livro = livro;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public List<Livro> getLivro() {
        return livro;
    }

    public void setLivro(List<Livro> livro) {
        this.livro = livro;
    }
}
