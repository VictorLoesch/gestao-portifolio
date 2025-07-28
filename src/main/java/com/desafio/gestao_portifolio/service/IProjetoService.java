package com.desafio.gestao_portifolio.service;

import java.util.List;

import com.desafio.gestao_portifolio.model.Projeto;

public interface IProjetoService {
	
	Projeto salvar(Projeto projeto, List<Long> membrosId);
    List<Projeto> listarTodos();
    Projeto buscarPorId(Long id);
    void excluir(Long id);

}
