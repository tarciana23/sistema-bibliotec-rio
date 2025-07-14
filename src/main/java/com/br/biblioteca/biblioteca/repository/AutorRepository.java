package com.br.biblioteca.biblioteca.repository;

import com.br.biblioteca.biblioteca.entity.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorRepository extends JpaRepository<Autor,Long> {
    boolean existsByNome(String nome);
    Autor findByNome(String nome);
}
