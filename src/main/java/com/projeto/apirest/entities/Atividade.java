package com.projeto.apirest.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import java.time.LocalDate;

@Entity
@Table(name = "tb_atividades")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Atividade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Column(name = "data_limite")
    private LocalDate dataLimite;

    private Boolean concluida = false;

    // Relacionamento: Muitas atividades pertencem a um Projeto
    @ManyToOne
    @JoinColumn(name = "projeto_id", nullable = false)
    private Projeto projeto;

    // Relacionamento: Muitas atividades pertencem a uma Pessoa (Responsável)
    @ManyToOne
    @JoinColumn(name = "pessoa_id")
    private Pessoa responsavel;
}