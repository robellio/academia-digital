package me.dio.academia.digital.controller;

import me.dio.academia.digital.entity.Aluno;
import me.dio.academia.digital.entity.AvaliacaoFisica;
import me.dio.academia.digital.entity.form.AlunoForm;
import me.dio.academia.digital.entity.form.AlunoUpdateForm;
import me.dio.academia.digital.service.impl.AlunoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoServiceImpl service;

    @GetMapping
    public List<Aluno> getAll(@RequestParam(value = "dataDeNascimento", required = false) String dataDeNascimento) {
        return service.getAll(dataDeNascimento);
    }

    @PostMapping
    public Aluno create(@Valid @RequestBody AlunoForm form) {
        return service.create(form);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> get(@PathVariable Long id) {
        Aluno aluno = service.get(id);
        if (aluno == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(aluno);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aluno> update(@PathVariable Long id, @RequestBody AlunoUpdateForm formUpdate) {
        Aluno aluno = service.update(id, formUpdate);
        if (aluno == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(aluno);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/avaliacoes/{id}")
    public List<AvaliacaoFisica> getAllAvaliacaoFisicaId(@PathVariable Long id) {
        return service.getAllAvaliacaoFisicaId(id);
   }
}
