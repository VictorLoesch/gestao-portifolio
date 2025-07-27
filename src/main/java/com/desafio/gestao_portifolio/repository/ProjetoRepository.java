package com.desafio.gestao_portifolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.desafio.gestao_portifolio.model.Projeto;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Long>{

}
