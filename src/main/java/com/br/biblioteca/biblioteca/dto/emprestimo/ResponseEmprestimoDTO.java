package com.br.biblioteca.biblioteca.dto.emprestimo;

import com.br.biblioteca.biblioteca.entity.Emprestimo;
import java.util.Date;

public record ResponseEmprestimoDTO(Long id, Long livroId, Long usuarioId, Date dataEmprestimo, Date dataDevolucao,
                                    com.br.biblioteca.biblioteca.entity.Status status) {
    public ResponseEmprestimoDTO(Emprestimo emprestimo){
        this(emprestimo.getId(),emprestimo.getLivroId(), emprestimo.getUsuarioId(),emprestimo.getDataEmprestimo(),emprestimo.getDataDevolucao(),emprestimo.getStatus());
    }
}
