package com.br.biblioteca.biblioteca.repository;

import com.br.biblioteca.biblioteca.entity.Emprestimo;
import com.br.biblioteca.biblioteca.entity.Livro;
import com.br.biblioteca.biblioteca.entity.Status;
import com.br.biblioteca.biblioteca.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmprestimoRepository extends JpaRepository<Emprestimo,Long> {
    int countByUsuarioIdAndStatus(Long idUsuario, Status status);

    List<Usuario> findByUsuarioId(Long idUsuario);

    List<Livro> findByLivroId(Long idLivro);

    boolean existsByUsuarioIdAndStatus(Long idUsuario, Status emAberto);
}
