package com.br.biblioteca.biblioteca.controller;


import com.br.biblioteca.biblioteca.dto.livro.RequestLivrosDTO;
import com.br.biblioteca.biblioteca.dto.livro.ResponseLivrosDTO;
import com.br.biblioteca.biblioteca.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livro")
public class Livrocontroller {
    @Autowired
    private LivroService service;

    @GetMapping("/listarTodos")
    public ResponseEntity<List<ResponseLivrosDTO>> listarLivros() {
        return ResponseEntity.ok(service.listarLivros());
    }

    @GetMapping("/buscarUmLivro/{id}")
    public ResponseEntity<ResponseLivrosDTO> buscarLivrosPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarLivroPorId(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<ResponseLivrosDTO> atualizarLivro(@PathVariable Long id, @RequestBody RequestLivrosDTO data) {
        ResponseLivrosDTO atualizarLivro = service.atualizarLivro(id, data);
        if (atualizarLivro != null) {
            return ResponseEntity.ok(atualizarLivro);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity cadastrarLivro(@RequestBody RequestLivrosDTO data) {
        service.cadastrarLivro(data);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity deletarLivro(@PathVariable Long id) {
        service.deletarLivro(id);
        return ResponseEntity.noContent().build();
    }

}
