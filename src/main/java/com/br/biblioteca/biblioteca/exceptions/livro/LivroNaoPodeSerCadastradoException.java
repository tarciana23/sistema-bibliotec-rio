package com.br.biblioteca.biblioteca.exceptions.livro;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class LivroNaoPodeSerCadastradoException extends RuntimeException {
    public LivroNaoPodeSerCadastradoException(String message) {
        super(message);
    }
}
