package com.br.biblioteca.biblioteca.exceptions.livro;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class LivroParaEmprestimoNotFound extends RuntimeException {
    public LivroParaEmprestimoNotFound(String message) {
        super(message);
    }
}
