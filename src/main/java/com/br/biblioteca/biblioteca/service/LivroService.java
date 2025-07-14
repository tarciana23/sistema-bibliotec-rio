package com.br.biblioteca.biblioteca.service;

import com.br.biblioteca.biblioteca.dto.livro.RequestLivrosDTO;
import com.br.biblioteca.biblioteca.dto.livro.ResponseLivrosDTO;
import com.br.biblioteca.biblioteca.entity.Autor;
import com.br.biblioteca.biblioteca.entity.Livro;
import com.br.biblioteca.biblioteca.exceptions.livro.LivroNaoPodeSerCadastradoException;
import com.br.biblioteca.biblioteca.exceptions.livro.LivroNaoPodeSerDeletado;
import com.br.biblioteca.biblioteca.exceptions.livro.LivroNotFoundException;
import com.br.biblioteca.biblioteca.repository.AutorRepository;
import com.br.biblioteca.biblioteca.repository.LivroRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private ObjectMapper objectMapper;

    public void cadastrarLivro(RequestLivrosDTO dadosLivro) {
        if (livroRepository.existsByTitulo(dadosLivro.titulo())) {
            throw new LivroNaoPodeSerCadastradoException("Já tem um livro cadastrado com esse título");
        }

        Autor autor = autorRepository.getReferenceById(dadosLivro.autorId());
        Livro novoLivro = new Livro();

        novoLivro.setTitulo(dadosLivro.titulo());
        novoLivro.setIsbn(dadosLivro.isbn());
        novoLivro.setAnoPublicacao(dadosLivro.anoPublicacao());
        novoLivro.setAutor(autor);
        novoLivro.setDisponivel(dadosLivro.disponivel());

        livroRepository.save(novoLivro);
    }

    public List<ResponseLivrosDTO> listarLivros() {
        return livroRepository.findAll()
                .stream()
                .map(ResponseLivrosDTO::new)
                .toList();
    }

    public ResponseLivrosDTO buscarLivroPorId(Long livroId) {
        var livroEncontrado = livroRepository.findById(livroId)
                .orElseThrow(() -> new LivroNotFoundException("Livro não encontrado"));

        return new ResponseLivrosDTO(livroEncontrado);
    }

    public ResponseLivrosDTO atualizarLivro(Long livroId, RequestLivrosDTO dadosAtualizados) {
        var livroExistente = livroRepository.findById(livroId)
                .orElseThrow(() -> new LivroNotFoundException("Livro não encontrado"));

        Autor autor = autorRepository.getReferenceById(dadosAtualizados.autorId());

        livroExistente.setTitulo(dadosAtualizados.titulo());
        livroExistente.setIsbn(dadosAtualizados.isbn());
        livroExistente.setAnoPublicacao(dadosAtualizados.anoPublicacao());
        livroExistente.setAutor(autor);
        livroExistente.setDisponivel(dadosAtualizados.disponivel());

        Livro livroAtualizado = livroRepository.save(livroExistente);

        return new ResponseLivrosDTO(livroAtualizado);
    }

    public void deletarLivro(Long livroId) {
        var livroParaDeletar = livroRepository.findById(livroId)
                .orElseThrow(() -> new LivroNaoPodeSerDeletado("Livro não pode ser excluído, pois não existe no banco de dados"));
        livroRepository.deleteById(livroId);
    }
}
