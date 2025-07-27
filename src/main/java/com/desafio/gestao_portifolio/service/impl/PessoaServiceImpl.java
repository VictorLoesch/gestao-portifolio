package com.desafio.gestao_portifolio.service.impl;

import java.util.List;

import com.desafio.gestao_portifolio.model.Pessoa;
import com.desafio.gestao_portifolio.repository.PessoaRepository;
import com.desafio.gestao_portifolio.service.PessoaService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PessoaServiceImpl implements PessoaService {

	private final PessoaRepository repo;
	
	@Override
	public Pessoa save(Pessoa pessoa) {
		return repo.save(pessoa);
	}

	@Override
	public List<Pessoa> findAll() {
		return repo.findAll();
	}

	@Override
	public Pessoa findById(Long id) {
		return repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Pessoa não encontrada"));
	}

	@Override
	public void delete(Long id) {
		repo.delete(findById(id));
	}

}
