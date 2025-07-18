package com.br.biblioteca.biblioteca.exceptions.autor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AutorNotFoundException extends RuntimeException{
    public AutorNotFoundException(String message){
        super(message);
    }
}
