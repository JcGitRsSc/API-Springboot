package com.projeto.apirest.controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.apirest.entities.Atividades;
import com.projeto.apirest.service.AtividadesService;

@RestController

@RequestMapping("/atividades")
public class PessoaController {

    private AtividadesService atividadesService;

    public PessoaController(AtividadesService atividadesService) {
        this.atividadesService = atividadesService;
    }

    @GetMapping
    public String getAtividades() {
        return "Lista de atividades";
    }

    @PostMapping
    public String CriarAtividade(@RequestBody Atividades atividades){
        return "Atividade Criada com sucesso ";
    }

    @PutMapping("/{id}")
    public String updateAtividade(PathVariable Long id, @RequestBody Atividade atividade){
        return "Atividade atualizada com sucesso";

    }

    @DeleteMapping("/{id}")
    public String DeleteAtividade(@PathVariable Long id){
        return "Atividade excluida";
    }
    
}
