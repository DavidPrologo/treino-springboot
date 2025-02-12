package com.example.demo.aluno;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;

@RestController
@RequestMapping("/alunos")
@Slf4j
public record AlunoController(AlunoRepository repository) {

    private static final Logger log = LoggerFactory.getLogger(AlunoController.class);

    @GetMapping("{id}")
    public Mono<AlunoEntity> buscarPorId(@PathVariable Long id){
        try {
            return repository.findById(id);
        }
        catch (Exception e){
            log.error("aluno não encontrado {id}", id, e);
        }
        return Mono.empty();
    }

    @GetMapping
    Flux<AlunoEntity> listarTodos(){
        return repository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Mono<AlunoEntity> adicionar(@RequestBody AlunoEntity alunoRequest){
        final var aluno = new AlunoEntity(alunoRequest.id(), alunoRequest.nome(), alunoRequest.telefone(), alunoRequest.email(),
                Instant.now());
        return repository.save(aluno);
    }

    @DeleteMapping("/{id}")
    Mono<Void> deletePorId(@PathVariable Long id){
        return repository.deleteById(id);
    }
}
