package com.br.biblioteca.biblioteca.controller;

import com.br.biblioteca.biblioteca.dto.usuario.RequestUsuarioDTO;
import com.br.biblioteca.biblioteca.dto.usuario.ResponseUsuarioDTO;
import com.br.biblioteca.biblioteca.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping
    public ResponseEntity<List<ResponseUsuarioDTO>> listarTodos(){
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/buscarUm/{id}")
    public ResponseEntity<ResponseUsuarioDTO> buscarPorId (@PathVariable Long id){
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity cadastrarUsuario(@RequestBody RequestUsuarioDTO dto){
        service.cadastrarUsuario(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("{id}")
    public ResponseEntity atualizarUsuario(@RequestBody RequestUsuarioDTO dto, @PathVariable Long id){
        service.atualizarUsuario(dto,id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity deletarUsuario(@PathVariable Long id){
        service.deletarUsuario(id);
        return ResponseEntity.noContent().build();
    }

}
