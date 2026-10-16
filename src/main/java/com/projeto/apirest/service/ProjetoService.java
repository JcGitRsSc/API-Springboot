package com.projeto.apirest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.projeto.apirest.Repository.AtividadesRepository;
import com.projeto.apirest.Repository.ProjetoRepository;
import com.projeto.apirest.entities.Projeto;
import com.projeto.apirest.entities.StatusProjeto;


@Service 
public class ProjetoService {
private final ProjetoRepository projetoRepository;
    private final AtividadesRepository atividadesRepository;

    public ProjetoService(ProjetoRepository projetoRepository, AtividadesRepository atividadesRepository) {
        this.projetoRepository = projetoRepository;
        this.atividadesRepository = atividadesRepository;
    }

    public List<Projeto> listarTodos() {
        return projetoRepository.findAll();
    }

    public Optional<Projeto> buscarPorId(Long id) {
        return projetoRepository.findById(id);
    }

    public Projeto salvar(Projeto projeto) {
        // Validação básica de datas
        if (projeto.getDataFim() != null && projeto.getDataFim().isBefore(projeto.getDataInicio())) {
            throw new IllegalArgumentException("A data de término não pode ser anterior à data de início.");
        }
        return projetoRepository.save(projeto);
    }

    public Projeto atualizar(Long id, Projeto projetoAtualizado) {
        Projeto projetoExistente = projetoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Projeto não encontrado com o ID: " + id));

        // Regra de Negócio: Impedir concluir projeto com atividades pendentes
        if (projetoAtualizado.getStatus() == StatusProjeto.CONCLUIDO) {
            boolean possuiPendentes = atividadesRepository.findByProjetoId(id).stream()
                    .anyMatch(atividade -> !atividade.getConcluida());
            
            if (possuiPendentes) {
                throw new IllegalStateException("Não é possível concluir o projeto pois existem atividades pendentes.");
            }
        }

        projetoExistente.setNome(projetoAtualizado.getNome());
        projetoExistente.setDescricao(projetoAtualizado.getDescricao());
        projetoExistente.setDataInicio(projetoAtualizado.getDataInicio());
        projetoExistente.setDataFim(projetoAtualizado.getDataFim());
        projetoExistente.setStatus(projetoAtualizado.getStatus());

        return projetoRepository.save(projetoExistente);
    }

    public void deletar(Long id) {
        if (!projetoRepository.existsById(id)) {
            throw new RuntimeException("Projeto não encontrado com o ID: " + id);
        }
        projetoRepository.deleteById(id);
    }
}
