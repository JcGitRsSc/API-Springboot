package com.projeto.apirest.service;

import com.projeto.apirest.entities.Atividade;
import com.projeto.apirest.Repository.AtividadesRepository;
import com.projeto.apirest.Repository.ProjetoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AtividadeService {

    private final AtividadesRepository atividadesRepository;
    private final ProjetoRepository projetoRepository;

    public AtividadeService(AtividadesRepository atividadesRepository, ProjetoRepository projetoRepository) {
        this.atividadesRepository = atividadesRepository;
        this.projetoRepository = projetoRepository;
    }

    public List<Atividade> listarTodas() {
        return atividadesRepository.findAll();
    }

    public List<Atividade> listarPorProjeto(Long projetoId) {
        return atividadesRepository.findByProjetoId(projetoId);
    }

    // Consulta específica solicitada no trabalho
    public List<Atividade> listarPendentesPorProjeto(Long projetoId) {
        return atividadesRepository.findByProjetoIdAndConcluidaFalse(projetoId);
    }

    public Atividade salvar(Atividade atividade) {
        // Regra de Negócio: Não permite atividade sem projeto válido
        if (atividade.getProjeto() == null || atividade.getProjeto().getId() == null) {
            throw new IllegalArgumentException("A atividade precisa estar associada a um projeto válido.");
        }
        
        Long projetoId = atividade.getProjeto().getId();
        if (!projetoRepository.existsById(projetoId)) {
            throw new RuntimeException("Projeto associado não existe.");
        }

        return atividadesRepository.save(atividade);
    }

    public Atividade atualizarStatus(Long id, Boolean concluida) {
        Atividade atividade = atividadesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Atividade não encontrada com o ID: " + id));

        atividade.setConcluida(concluida);
        return atividadesRepository.save(atividade);
    }

    public void deletar(Long id) {
        if (!atividadesRepository.existsById(id)) {
            throw new RuntimeException("Atividade não encontrada com o ID: " + id);
        }
        atividadesRepository.deleteById(id);
    }
}