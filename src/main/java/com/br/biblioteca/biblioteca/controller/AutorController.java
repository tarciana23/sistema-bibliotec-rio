package com.br.biblioteca.biblioteca.controller;

import com.br.biblioteca.biblioteca.dto.autor.RequestAutorDTO;
import com.br.biblioteca.biblioteca.dto.autor.ResponseAutorDTO;
import com.br.biblioteca.biblioteca.service.AutorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("autor")
public class AutorController {

    @Autowired
    private AutorService service;

    @GetMapping("/listarTodos")
    public ResponseEntity<List<ResponseAutorDTO>> listarAutores(){
        return ResponseEntity.ok(service.listarAutores());
    }

    @GetMapping("/buscarUmAutor/{id}")
    public ResponseEntity<ResponseAutorDTO> buscarAutorPorNome(@PathVariable Long id){
        return  ResponseEntity.ok(service.buscarAutorPorId(id));
    }

    @PostMapping("/cadastrarAutor")
    public ResponseEntity cadastrarAutor(@RequestBody @Valid RequestAutorDTO data){
        service.cadastrarAutor(data);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("{id}")
    public  ResponseEntity<ResponseAutorDTO> atualizarLivro(@PathVariable Long id, @RequestBody RequestAutorDTO data){
        ResponseAutorDTO atualizarAutor = service.atualizarAutor(id,data);
        if(atualizarAutor != null){
            return ResponseEntity.ok(atualizarAutor);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity deletarAutor(@PathVariable Long id){
       service.deletarAutorPorId(id);
       return ResponseEntity.noContent().build();
    }
}
