package com.br.biblioteca.biblioteca.dto.autor;

import com.br.biblioteca.biblioteca.entity.Autor;

import java.util.List;

public record ResponseAutorDTO(Long id, String nome, String nacionalidade, List Livro) {
    public ResponseAutorDTO(Autor autor){
        this(autor.getId(),autor.getNome(),autor.getNacionalidade(),autor.getLivro());
    }
}
