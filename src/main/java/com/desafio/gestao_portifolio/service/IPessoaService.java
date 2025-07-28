package com.desafio.gestao_portifolio.service;

import java.util.List;

import com.desafio.gestao_portifolio.model.Pessoa;

public interface IPessoaService {

	Pessoa salvar(Pessoa pessoa);
	List<Pessoa> listarTodos();
	Pessoa buscarPorId(Long id);
	void excluir(Long id);
}
