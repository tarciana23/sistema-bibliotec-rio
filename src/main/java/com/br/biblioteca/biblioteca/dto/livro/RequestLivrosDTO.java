package com.br.biblioteca.biblioteca.dto.livro;

public record RequestLivrosDTO(String titulo, String isbn, Integer anoPublicacao, Long autorId, boolean disponivel) {
}
