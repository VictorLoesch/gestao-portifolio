package com.desafio.gestao_portifolio.service.impl;

import java.util.List;

import com.desafio.gestao_portifolio.model.Projeto;
import com.desafio.gestao_portifolio.model.enums.StatusProjeto;
import com.desafio.gestao_portifolio.repository.ProjetoRepository;
import com.desafio.gestao_portifolio.service.ProjetoService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ProjetoServiceImpl implements ProjetoService {
	
	private final ProjetoRepository repo;
	
	@Override
	public Projeto save(Projeto projeto) {
		return repo.save(projeto);
	}

	@Override
	public List<Projeto> findAll() {
		return repo.findAll();
	}

	@Override
	public Projeto findById(Long id) {
		return repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Projeto não encotrado"));
	}

	@Override
	public void delete(Long id) {
		Projeto p = findById(id);
		if (p.getStatus() == StatusProjeto.INICIADO || p.getStatus() ==
				StatusProjeto.EM_ANDAMENTO || p.getStatus() == StatusProjeto.ENCERRADO) {
					throw new IllegalStateException("Não é possível excluir projeto iniciado, em andamento ou encerrado");
		}
		repo.delete(p);
	}

}
