package com.br.biblioteca.biblioteca.dto.usuario;

import com.br.biblioteca.biblioteca.entity.Usuario;

public record ResponseUsuarioDTO(Long id, String nome, String email,String telefone) {
    public ResponseUsuarioDTO(Usuario usuario){
        this(usuario.getId(), usuario.getNome(), usuario.getEmail(),usuario.getTelefone());
    }
}
