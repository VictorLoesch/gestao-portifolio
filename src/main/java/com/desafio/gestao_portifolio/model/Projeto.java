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
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "projeto")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Projeto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotBlank(message = "O nome do projeto é obrigatório")
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

    @Positive(message = "O orçamento deve ser maior que zero")
    @Column(nullable = false)
    private Float orcamento;

    @Enumerated(EnumType.STRING)
    @Column(length = 45)
    private NivelRisco risco;

    @NotNull(message = "O gerente é obrigatório")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "idgerente", nullable = false, foreignKey = @ForeignKey(name = "fk_gerente"))
    @ToString.Exclude
    private Pessoa gerente;

    @ManyToMany
    @JoinTable(
      name = "membros",                            
      joinColumns = @JoinColumn(name = "idprojeto"),
      inverseJoinColumns = @JoinColumn(name = "idpessoa")
    )
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Pessoa> membros = new HashSet<>();

    public void addMembro(Pessoa p) {
        if (!membros.contains(p) && Boolean.TRUE.equals(p.isFuncionario())) {
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
