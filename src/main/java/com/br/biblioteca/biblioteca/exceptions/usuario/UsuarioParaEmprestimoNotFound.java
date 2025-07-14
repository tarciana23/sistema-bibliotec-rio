package com.br.biblioteca.biblioteca.exceptions.usuario;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UsuarioParaEmprestimoNotFound extends RuntimeException {
    public UsuarioParaEmprestimoNotFound(String message) {
        super(message);
    }
}
