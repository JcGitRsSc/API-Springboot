package com.projeto.apirest.controller;

@RestController
@RequestMapping("/projetos")
public class ProjetoController {

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
