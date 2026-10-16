package com.projeto.apirest.controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;
import com.projeto.apirest.entities.Atividade;
import com.projeto.apirest.service.PessoaService;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    // 1. Declaração do atributo (faltava isto!)
    private final PessoaService pessoaService;

    // 2. Injeção via construtor
    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @GetMapping
    public String getPessoas() {
        return "Lista de pessoas";
    }
}