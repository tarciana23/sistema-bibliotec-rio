package com.br.biblioteca.biblioteca.exceptions.autor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class AutorComLivrosException extends RuntimeException {
  public AutorComLivrosException(String message) {
    super(message);
  }
}
