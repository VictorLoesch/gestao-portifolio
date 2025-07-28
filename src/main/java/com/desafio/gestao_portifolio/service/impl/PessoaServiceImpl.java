package com.desafio.gestao_portifolio.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.desafio.gestao_portifolio.model.Pessoa;
import com.desafio.gestao_portifolio.repository.PessoaRepository;
import com.desafio.gestao_portifolio.service.IPessoaService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PessoaServiceImpl implements IPessoaService {

	private final PessoaRepository repositorio;
	
	@Override
    public Pessoa salvar(Pessoa pessoa) {
        return repositorio.save(pessoa);
    }

    @Override
    public List<Pessoa> listarTodos() {
        return repositorio.findAll();
    }

    @Override
    public Pessoa buscarPorId(Long id) {
        return repositorio.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Pessoa não encontrada"));
    }

    @Override
    public void excluir(Long id) {
        repositorio.delete(buscarPorId(id));
    }

}
