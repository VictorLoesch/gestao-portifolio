package com.desafio.gestao_portifolio.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;

import com.desafio.gestao_portifolio.model.Pessoa;
import com.desafio.gestao_portifolio.model.Projeto;
import com.desafio.gestao_portifolio.model.enums.StatusProjeto;
import com.desafio.gestao_portifolio.repository.ProjetoRepository;
import com.desafio.gestao_portifolio.service.IPessoaService;
import com.desafio.gestao_portifolio.service.IProjetoService;

import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProjetoServiceImpl implements IProjetoService {
	
	private final ProjetoRepository repositorio;
	private final IPessoaService pessoaService;
	
	@Override
    @Transactional
    public Projeto salvar(Projeto projeto, List<Long> membrosId) {
        Projeto salvo = repositorio.saveAndFlush(projeto);

        salvo.getMembros().clear();
        if (membrosId != null) {
            for (Long mid : membrosId) {
                Pessoa membro = pessoaService.buscarPorId(mid);
                salvo.addMembro(membro);
            }
        }
        return repositorio.save(salvo);
    }

    @Override
    public List<Projeto> listarTodos() {
        return repositorio.findAll();
    }

    @Override
    public Projeto buscarPorId(Long id) {
        return repositorio.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Projeto não encontrado"));
    }

    @Override
    public void excluir(Long id) {
        Projeto projeto = buscarPorId(id);
        if (projeto.getStatus() == StatusProjeto.INICIADO || projeto.getStatus() == StatusProjeto.EM_ANDAMENTO || projeto.getStatus() == StatusProjeto.ENCERRADO) {
            throw new IllegalStateException("Não é possível excluir projeto iniciado, em andamento ou encerrado");
        }
        repositorio.delete(projeto);
    }


}
