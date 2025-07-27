package com.desafio.gestao_portifolio.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "pessoa")
@Data
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String nome;

    private LocalDate dataNascimento;

    @Column(length = 14)
    private String cpf;

    @Column(nullable = false)
    private Boolean funcionario;

    @Column(nullable = false)
    private Boolean gerente;

    @OneToMany(mappedBy = "gerente", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Projeto> projetosGerenciados = new HashSet<>();

    @ManyToMany
    @JoinTable(
        name = "membros",
        joinColumns = @JoinColumn(name = "idpessoa"),
        inverseJoinColumns = @JoinColumn(name = "idprojeto")
    )
    private Set<Projeto> projetos = new HashSet<>();
    
}