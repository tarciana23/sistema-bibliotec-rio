package com.br.biblioteca.biblioteca.repository;

import com.br.biblioteca.biblioteca.entity.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro,Long> {
    boolean existsByTitulo(String titulo);
}
