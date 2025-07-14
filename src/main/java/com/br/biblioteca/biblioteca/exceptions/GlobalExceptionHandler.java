package com.br.biblioteca.biblioteca.exceptions;

import com.br.biblioteca.biblioteca.exceptions.autor.AutorComLivrosException;
import com.br.biblioteca.biblioteca.exceptions.autor.AutorNotFoundException;
import com.br.biblioteca.biblioteca.exceptions.livro.LivroNaoPodeSerCadastradoException;
import com.br.biblioteca.biblioteca.exceptions.livro.LivroNaoPodeSerDeletado;
import com.br.biblioteca.biblioteca.exceptions.livro.LivroNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AutorNotFoundException.class)
    public ResponseEntity<Object> handleAutorNotFound(com.br.biblioteca.biblioteca.exceptions.autor.AutorNotFoundException ex, WebRequest request) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(AutorComLivrosException.class)
    public ResponseEntity<Object> handleAutorComLivros(AutorComLivrosException ex, WebRequest request) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY, request);
    }

    @ExceptionHandler(LivroNaoPodeSerCadastradoException.class)
    public ResponseEntity<Object> handleLivroNaoPodeSerCadastrado(LivroNaoPodeSerCadastradoException ex, WebRequest request) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY, request);
    }

    @ExceptionHandler(LivroNaoPodeSerDeletado.class)
    public ResponseEntity<Object> handleLivroNaoPodeSerDeletado(com.br.biblioteca.biblioteca.exceptions.livro.LivroNaoPodeSerDeletado ex, WebRequest request) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY, request);
    }

    @ExceptionHandler(LivroNotFoundException.class)
    public ResponseEntity<Object> handleLivroNotFound(LivroNotFoundException ex, WebRequest request) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND, request);
    }

    private ResponseEntity<Object> buildErrorResponse(String message, HttpStatus status, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("Status", status.value());
        body.put("error", status.getReasonPhrase());
        body.put("message", message);
        body.put("path", request.getDescription(false).replace("uri=", ""));

        return new ResponseEntity<>(body, status);
    }
}
