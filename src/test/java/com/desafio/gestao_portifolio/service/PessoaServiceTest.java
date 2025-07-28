package com.desafio.gestao_portifolio.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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

import com.desafio.gestao_portifolio.model.Pessoa;
import com.desafio.gestao_portifolio.repository.PessoaRepository;
import com.desafio.gestao_portifolio.service.impl.PessoaServiceImpl;

public class PessoaServiceTest {

	
	@Mock
    private PessoaRepository repositorio;

    @InjectMocks
    private PessoaServiceImpl service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveSalvarPessoa() {
        Pessoa p = new Pessoa();
        p.setNome("Ana");
        p.setFuncionario(true);
        p.setGerente(false);
        when(repositorio.save(p)).thenReturn(p);

        Pessoa salvo = service.salvar(p);
        assertNotNull(salvo);
        assertEquals("Ana", salvo.getNome());
        verify(repositorio).save(p);
    }

    @Test
    void deveListarTodasPessoas() {
        Pessoa p1 = new Pessoa(); p1.setNome("A");
        Pessoa p2 = new Pessoa(); p2.setNome("B");
        when(repositorio.findAll()).thenReturn(Arrays.asList(p1, p2));

        var lista = service.listarTodos();
        assertEquals(2, lista.size());
        verify(repositorio).findAll();
    }

    @Test
    void deveLancarAoBuscarPorIdInexistente() {
        when(repositorio.findById(99L)).thenReturn(Optional.empty());
        var ex = assertThrows(IllegalArgumentException.class, () -> service.buscarPorId(99L));
        assertEquals("Pessoa não encontrada", ex.getMessage());
    }

    @Test
    void deveExcluirPessoaExistente() {
        Pessoa p = new Pessoa(); p.setId(5L);
        when(repositorio.findById(5L)).thenReturn(Optional.of(p));

        service.excluir(5L);
        verify(repositorio).delete(p);
    }
	
}
