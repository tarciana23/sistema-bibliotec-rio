package com.br.biblioteca.biblioteca.service;

import com.br.biblioteca.biblioteca.dto.emprestimo.RequestEmprestimoDTO;
import com.br.biblioteca.biblioteca.dto.emprestimo.ResponseEmprestimoDTO;
import com.br.biblioteca.biblioteca.entity.Emprestimo;
import com.br.biblioteca.biblioteca.entity.Livro;
import com.br.biblioteca.biblioteca.entity.Status;
import com.br.biblioteca.biblioteca.exceptions.emprestimo.EmprestimoInvalido;
import com.br.biblioteca.biblioteca.exceptions.emprestimo.EmprestimoNotFound;
import com.br.biblioteca.biblioteca.exceptions.livro.LivroParaEmprestimoNotFound;
import com.br.biblioteca.biblioteca.exceptions.usuario.UsuarioParaEmprestimoNotFound;
import com.br.biblioteca.biblioteca.repository.EmprestimoRepository;
import com.br.biblioteca.biblioteca.repository.LivroRepository;
import com.br.biblioteca.biblioteca.repository.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EmprestimoService {

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ObjectMapper objectMapper;

    public void registrarEmprestimo(RequestEmprestimoDTO dadosEmprestimo) {
        Long livroId = dadosEmprestimo.livroId();
        Long usuarioId = dadosEmprestimo.usuarioId();

        var livro = livroRepository.findById(livroId)
                .orElseThrow(() -> new LivroParaEmprestimoNotFound("Livro não encontrado!"));

        var usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new UsuarioParaEmprestimoNotFound("Usuário não encontrado!"));

        if (!livro.isDisponivel()) {
            throw new EmprestimoInvalido("Esse livro já está emprestado");
        }

        int quantidadeEmprestimosAbertos = emprestimoRepository.countByUsuarioIdAndStatus(usuarioId, Status.EM_ABERTO);

        if (quantidadeEmprestimosAbertos >= 3) {
            throw new EmprestimoInvalido("Usuário já atingiu o limite de 3 livros emprestados.");
        }

        var novoEmprestimo = objectMapper.convertValue(dadosEmprestimo, Emprestimo.class);
        novoEmprestimo.setStatus(Status.EM_ABERTO);
        emprestimoRepository.save(novoEmprestimo);

        livro.setDisponivel(false);
        livroRepository.save(livro);
    }

    public List<ResponseEmprestimoDTO> listarTodosEmprestimos() {
        return emprestimoRepository.findAll()
                .stream()
                .map(ResponseEmprestimoDTO::new)
                .toList();
    }

    public ResponseEmprestimoDTO buscarEmprestimoPorUsuario(Long usuarioId) {
        var emprestimoOptional = emprestimoRepository.findById(usuarioId);
        return objectMapper.convertValue(emprestimoOptional, ResponseEmprestimoDTO.class);
    }

    public ResponseEmprestimoDTO buscarEmprestimoPorLivro(Long livroId) {
        var emprestimoOptional = emprestimoRepository.findById(livroId);
        return objectMapper.convertValue(emprestimoOptional, ResponseEmprestimoDTO.class);
    }

    public void devolverLivro(Long emprestimoId) {
        var emprestimo = emprestimoRepository.findById(emprestimoId)
                .orElseThrow(() -> new EmprestimoNotFound("Esse id de empréstimo não existe"));

        emprestimo.setStatus(Status.DEVOLVIDO);
        emprestimo.setDataDevolucao(new Date());

        emprestimoRepository.save(emprestimo);

        Long livroId = emprestimo.getLivroId();
        var livroOptional = livroRepository.findById(livroId);
        var livro = objectMapper.convertValue(livroOptional, Livro.class);
        livro.setDisponivel(true);
        livroRepository.save(livro);
    }

    public boolean usuarioTemEmprestimo(Long usuarioId) {
        return emprestimoRepository.existsByUsuarioIdAndStatus(usuarioId, Status.EM_ABERTO);
    }
}
