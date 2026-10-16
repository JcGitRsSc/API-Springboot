package com.projeto.apirest.Repository;
import com.projeto.apirest.entities.Atividade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AtividadesRepository extends JpaRepository<Atividade, Long> {
    List<Atividade> findByProjetoId(Long projetoId);
    List<Atividade> findByProjetoIdAndConcluidaFalse(Long projetoId);
}