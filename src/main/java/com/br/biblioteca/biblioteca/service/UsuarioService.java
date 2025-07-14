package com.br.biblioteca.biblioteca.service;

import com.br.biblioteca.biblioteca.dto.usuario.RequestUsuarioDTO;
import com.br.biblioteca.biblioteca.dto.usuario.ResponseUsuarioDTO;
import com.br.biblioteca.biblioteca.entity.Usuario;
import com.br.biblioteca.biblioteca.exceptions.autor.AutorNotFoundException;
import com.br.biblioteca.biblioteca.exceptions.usuario.UsuarioNotFound;
import com.br.biblioteca.biblioteca.repository.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ObjectMapper objectMapper;

    public List<ResponseUsuarioDTO> listarTodos() {
        return usuarioRepository.findAll()
                .stream()
                .map(ResponseUsuarioDTO::new)
                .toList();
    }

    public ResponseUsuarioDTO buscarPorId(Long usuarioId) {
        var usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new UsuarioNotFound("Não tem usuário cadastrado com esse id!"));
        return objectMapper.convertValue(usuario, ResponseUsuarioDTO.class);
    }

    public void cadastrarUsuario(RequestUsuarioDTO dadosUsuario) {
        if (usuarioRepository.existsByNome(dadosUsuario.nome())) {
            throw new AutorNotFoundException("Já tem um autor cadastrado com esse nome");
        }
        var novoUsuario = objectMapper.convertValue(dadosUsuario, Usuario.class);
        usuarioRepository.save(novoUsuario);
    }

    public ResponseUsuarioDTO atualizarUsuario(RequestUsuarioDTO dadosAtualizados, Long usuarioId) {
        var usuarioExistente = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new UsuarioNotFound("Não tem usuário cadastrado com esse id!"));

        usuarioExistente.setNome(dadosAtualizados.nome());
        usuarioExistente.setEmail(dadosAtualizados.email());
        usuarioExistente.setTelefone(dadosAtualizados.telefone());

        usuarioRepository.save(usuarioExistente);

        return objectMapper.convertValue(usuarioExistente, ResponseUsuarioDTO.class);
    }

    public void deletarUsuario(Long usuarioId) {
        var usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new UsuarioNotFound("Não tem usuário cadastrado com esse id!"));
        usuarioRepository.deleteById(usuarioId);
    }
}

