package com.br.biblioteca.biblioteca.exceptions.livro;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class LivroNaoPodeSerDeletado extends RuntimeException {
    public LivroNaoPodeSerDeletado(String message) {
        super(message);
    }
}
