package com.desafio.gestao_portifolio.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.desafio.gestao_portifolio.model.Projeto;
import com.desafio.gestao_portifolio.model.enums.StatusProjeto;
import com.desafio.gestao_portifolio.repository.ProjetoRepository;
import com.desafio.gestao_portifolio.service.impl.ProjetoServiceImpl;

public class ProjetoServiceTest {
	
	@Mock
    private ProjetoRepository repositorio;

    @InjectMocks
    private ProjetoServiceImpl service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveSalvarProjeto() {
        Projeto p = new Projeto();
        p.setNome("Proj");

        when(repositorio.saveAndFlush(p)).thenReturn(p);
        when(repositorio.save(p)).thenReturn(p);

        Projeto salvo = service.salvar(p, null);        
        assertEquals("Proj", salvo.getNome());
        verify(repositorio).saveAndFlush(p);
        verify(repositorio).save(p);
    }

    @Test
    void deveListarTodosProjetos() {
        Projeto p1 = new Projeto(); p1.setNome("A");
        Projeto p2 = new Projeto(); p2.setNome("B");
        when(repositorio.findAll()).thenReturn(Arrays.asList(p1, p2));

        var lista = service.listarTodos();
        assertEquals(2, lista.size());
        assertEquals("A", lista.get(0).getNome());
        assertEquals("B", lista.get(1).getNome());
        verify(repositorio).findAll();
    }
    
    @Test
    void naoDeveExcluirProjetoComStatusInvalido() {
        Projeto p = new Projeto(); p.setId(1L); p.setStatus(StatusProjeto.INICIADO);
        when(repositorio.findById(1L)).thenReturn(Optional.of(p));

        var ex = assertThrows(IllegalStateException.class, () -> service.excluir(1L));
        assertEquals("Não é possível excluir projeto iniciado, em andamento ou encerrado", ex.getMessage());
    }

    @Test
    void deveExcluirProjetoPlanejado() {
        Projeto p = new Projeto(); p.setId(2L); p.setStatus(StatusProjeto.PLANEJADO);
        when(repositorio.findById(2L)).thenReturn(Optional.of(p));

        service.excluir(2L);
        verify(repositorio).delete(p);
    }

}
