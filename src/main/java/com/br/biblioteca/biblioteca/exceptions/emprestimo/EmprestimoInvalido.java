package com.br.biblioteca.biblioteca.exceptions.emprestimo;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class EmprestimoInvalido extends RuntimeException {
    public EmprestimoInvalido(String message) {
        super(message);
    }
}
