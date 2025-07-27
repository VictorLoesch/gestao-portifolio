package com.desafio.gestao_portifolio.service;

import java.util.List;

import com.desafio.gestao_portifolio.model.Pessoa;

public interface PessoaService {

	Pessoa save(Pessoa pessoa);
	List<Pessoa> findAll();
	Pessoa findById(Long id);
	void delete(Long id);

}
