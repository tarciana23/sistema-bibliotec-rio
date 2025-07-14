package com.br.biblioteca.biblioteca.dto.livro;

import com.br.biblioteca.biblioteca.entity.Livro;

public record ResponseLivrosDTO(
        Long idLivro,
        String titulo,
        String isbn,
        Integer anoPublicacao,
        Long autorId,
        boolean disponivel
) {
    public ResponseLivrosDTO(Livro livro) {
        this(
                livro.getIdLivro(),
                livro.getTitulo(),
                livro.getIsbn(),
                livro.getAnoPublicacao(),
                livro.getAutor() != null ? livro.getAutor().getId() : null,
                livro.isDisponivel()
        );
    }
}
