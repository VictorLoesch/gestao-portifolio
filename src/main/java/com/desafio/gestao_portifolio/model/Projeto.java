package com.desafio.gestao_portifolio.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.desafio.gestao_portifolio.model.enums.NivelRisco;
import com.desafio.gestao_portifolio.model.enums.StatusProjeto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "projeto")
@Data
public class Projeto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 200, nullable = false)
    private String nome;

    private LocalDate dataInicio;

    private LocalDate dataPrevisaoFim;

    private LocalDate dataFim;

    @Column(length = 5000)
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 45)
    private StatusProjeto status;

    private Float orcamento;

    @Enumerated(EnumType.STRING)
    @Column(length = 45)
    private NivelRisco risco;

    @ManyToOne(optional = false)
    @JoinColumn(name = "idgerente", nullable = false, foreignKey = @ForeignKey(name = "fk_gerente"))
    private Pessoa gerente;

    @ManyToMany(mappedBy = "projetos")
    private Set<Pessoa> membros = new HashSet<>();

    public void addMembro(Pessoa p) {
        if (!membros.contains(p) && Boolean.TRUE.equals(p.getFuncionario())) {
            membros.add(p);
            p.getProjetos().add(this);
        }
    }

    public void removeMembro(Pessoa p) {
        if (membros.remove(p)) {
            p.getProjetos().remove(this);
        }
    }
}
