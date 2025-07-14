package com.br.biblioteca.biblioteca.entity;

import jakarta.persistence.*;

import com.br.biblioteca.biblioteca.entity.Status;
import java.util.Date;

@Entity(name = "emprestimos")
@Table(name = "emprestimos")
public class Emprestimo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "idLivro", nullable = false)
    private Long livroId;

    @Column(name = "idUsuario", nullable = false)
    private Long usuarioId;

    private Date dataEmprestimo;
    private Date dataDevolucao;
    private com.br.biblioteca.biblioteca.entity.Status status;

    public Emprestimo(Long id, Long livroId, Long usuarioId, Date dataEmprestimo, Date dataDevolucao, Status status) {
        this.id = id;
        this.livroId = livroId;
        this.usuarioId = usuarioId;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
        this.status = status;
    }

    public Emprestimo() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLivroId() {
        return livroId;
    }

    public void setLivroId(Long livroId) {
        this.livroId = livroId;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Date getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(Date dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
