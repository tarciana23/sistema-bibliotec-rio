package com.br.biblioteca.biblioteca.controller;

import com.br.biblioteca.biblioteca.service.EmprestimoService;
import com.br.biblioteca.biblioteca.dto.emprestimo.RequestEmprestimoDTO;
import com.br.biblioteca.biblioteca.dto.emprestimo.ResponseEmprestimoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emprestimos")
public class EmprestimoController {

    @Autowired
    private EmprestimoService service;

    @PostMapping
    public ResponseEntity<String> registrarEmprestimo(@RequestBody RequestEmprestimoDTO dto) {
        try {
            service.registrarEmprestimo(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Empréstimo registrado com sucesso.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<ResponseEmprestimoDTO>> listarTodosEmprestimos() {
        var lista = service.listarTodosEmprestimos();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<?> buscarEmprestimoPorUsuario(@PathVariable Long idUsuario) {
        try {
            var dto = service.buscarEmprestimoPorUsuario(idUsuario);
            return ResponseEntity.ok(dto);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/livro/{idLivro}")
    public ResponseEntity<?> buscarEmprestimoPorLivro(@PathVariable Long idLivro) {
        try {
            var dto = service.buscarEmprestimoPorLivro(idLivro);
            return ResponseEntity.ok(dto);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/devolver/{idEmprestimo}")
    public ResponseEntity<String> devolverLivro(@PathVariable Long idEmprestimo) {
        try {
            service.devolverLivro(idEmprestimo);
            return ResponseEntity.ok("Livro devolvido com sucesso.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/usuario/{idUsuario}/tem-emprestimo")
    public ResponseEntity<Boolean> usuarioTemEmprestimo(@PathVariable Long idUsuario) {
        boolean temEmprestimo = service.usuarioTemEmprestimo(idUsuario);
        return ResponseEntity.ok(temEmprestimo);
    }
}

