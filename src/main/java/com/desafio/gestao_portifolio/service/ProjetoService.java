package com.desafio.gestao_portifolio.service;

import java.util.List;

import com.desafio.gestao_portifolio.model.Projeto;

public interface ProjetoService {
	Projeto save(Projeto projeto);
	List<Projeto> findAll();
	Projeto findById(Long id);
	void delete(Long id);

}
