package com.br.biblioteca.biblioteca.service;

import com.br.biblioteca.biblioteca.dto.autor.RequestAutorDTO;
import com.br.biblioteca.biblioteca.dto.autor.ResponseAutorDTO;
import com.br.biblioteca.biblioteca.entity.Autor;
import com.br.biblioteca.biblioteca.exceptions.autor.AutorComLivrosException;
import com.br.biblioteca.biblioteca.exceptions.autor.AutorNotFoundException;
import com.br.biblioteca.biblioteca.repository.AutorRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private ObjectMapper objectMapper;

    public void cadastrarAutor(RequestAutorDTO dadosAutor) {
        if (autorRepository.existsByNome(dadosAutor.nome())) {
            throw new AutorNotFoundException("Já tem um autor cadastrado com esse nome");
        }
        var novoAutor = new Autor(dadosAutor);
        autorRepository.save(novoAutor);
    }

    public List<ResponseAutorDTO> listarAutores() {
        return autorRepository.findAll()
                .stream()
                .map(ResponseAutorDTO::new)
                .toList();
    }

    public ResponseAutorDTO buscarAutorPorId(Long idAutor) {
        var autorEncontrado = autorRepository.findById(idAutor)
                .orElseThrow(() -> new AutorNotFoundException("Esse autor não existe!"));
        return objectMapper.convertValue(autorEncontrado, ResponseAutorDTO.class);
    }

    public ResponseAutorDTO atualizarAutor(Long idAutor, RequestAutorDTO dadosAtualizados) {
        var autorExistente = autorRepository.findById(idAutor)
                .orElseThrow(() -> new AutorNotFoundException("Não existe um autor cadastrado com esse id"));

        autorExistente.setNome(dadosAtualizados.nome());
        autorExistente.setNacionalidade(dadosAtualizados.nacionalidade());

        var autorAtualizado = autorRepository.save(autorExistente);
        return new ResponseAutorDTO(autorAtualizado);
    }

    public void deletarAutorPorId(Long idAutor) {
        var autorParaDeletar = autorRepository.findById(idAutor)
                .orElseThrow(() -> new AutorNotFoundException("Não existe um autor cadastrado com esse id"));

        int quantidadeLivros = autorParaDeletar.getLivro().size();

        if (quantidadeLivros > 0) {
            throw new AutorComLivrosException("Esse autor não pode ser deletado, pois possui livros cadastrados!");
        }

        autorRepository.deleteById(idAutor);
    }
}

