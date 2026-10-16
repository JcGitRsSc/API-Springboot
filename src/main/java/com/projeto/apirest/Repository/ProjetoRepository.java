package com.projeto.apirest.Repository;

import java.util.List;
import java.util.Optional;
import com.projeto.apirest.entities.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository 

public interface ProjetoRepository extends JpaRepository<Projeto, Long> {
    List<Projeto> findAll();

    Optional<Projeto> findById(Long id);

    Projeto save(Projeto projeto);

    boolean existsById(Long projetoId);

}
