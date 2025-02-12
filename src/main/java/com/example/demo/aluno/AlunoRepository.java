package com.example.demo.aluno;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

interface AlunoRepository extends R2dbcRepository<AlunoEntity, Long> {
}