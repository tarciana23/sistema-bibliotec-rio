package com.br.biblioteca.biblioteca.dto.emprestimo;

import java.util.Date;

public record RequestEmprestimoDTO(Long livroId, Long usuarioId, Date dataEmprestimo) {
}