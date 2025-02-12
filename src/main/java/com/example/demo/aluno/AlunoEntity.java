package com.example.demo.aluno;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;

@Table(name = "alunos")
public record AlunoEntity(
        @Id
        Long id,
        String nome,
        String telefone,
        String email,
        Instant dataCadastro
        ) {

}
