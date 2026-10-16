package com.projeto.apirest.controller;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.apirest.entities.Atividade;
import com.projeto.apirest.service.AtividadeService;

@RestController
@RequestMapping("/atividades")
public class AtividadesController {

    private final AtividadeService atividadeService;

    public AtividadesController(AtividadeService atividadeService) {
        this.atividadeService = atividadeService;
    }

    @GetMapping
    public List<Atividade> listarTodas() {
        return atividadeService.listarTodas();
    }
    // Endpoint para a consulta específica: /atividades/projeto/1/pendentes
    @GetMapping("/projeto/{projetoId}/pendentes")
    public List<Atividade> listarPendentesPorProjeto(@PathVariable Long projetoId) {
        return atividadeService.listarPendentesPorProjeto(projetoId);
    }
    @PostMapping
    public ResponseEntity<?> criar(@RequestBody Atividade atividade) {
        try {
            Atividade novaAtividade = atividadeService.salvar(atividade);
            return ResponseEntity.status(HttpStatus.CREATED).body(novaAtividade);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<?> atualizarStatus(@PathVariable Long id, @RequestParam Boolean concluida) {
        try {
            Atividade atividadeAtualizada = atividadeService.atualizarStatus(id, concluida);
            return ResponseEntity.ok(atividadeAtualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
    }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            atividadeService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }    
}
