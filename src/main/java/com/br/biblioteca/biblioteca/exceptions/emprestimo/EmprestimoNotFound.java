package com.br.biblioteca.biblioteca.exceptions.emprestimo;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EmprestimoNotFound extends RuntimeException {
  public EmprestimoNotFound(String message) {
    super(message);
  }
}
