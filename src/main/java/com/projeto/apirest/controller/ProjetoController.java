package com.projeto.apirest.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.apirest.entities.Projeto;
import com.projeto.apirest.service.ProjetoService;

@RestController
@RequestMapping("/projetos")
public class ProjetoController {

    private ProjetoService projetoService;
    public ProjetoController(ProjetoService projetoService) {
        this.projetoService = projetoService;
    }

    @GetMapping
    public String getProjetos() {
        return "Lista de projetos";
    }

    @PostMapping
    public String createProjeto(@RequestBody Projeto projeto) {
        return "Projeto criado com sucesso";
    }

    @PutMapping("/{id}")
    public String updateProjeto(@PathVariable Long id, @RequestBody Projeto projeto) {
        return "Projeto atualizado com sucesso";
    }
    @DeleteMapping("/{id}")
    public String deleteProjeto(@PathVariable Long id) {
        return "Projeto deletado com sucesso";
    }
}
