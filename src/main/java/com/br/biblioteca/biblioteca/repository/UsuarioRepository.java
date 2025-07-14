package com.br.biblioteca.biblioteca.repository;

import com.br.biblioteca.biblioteca.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    boolean existsByNome(String nome);
}
