package com.desafio.gestao_portifolio.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.desafio.gestao_portifolio.model.Pessoa;
import com.desafio.gestao_portifolio.service.IPessoaService;

import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(PessoaController.class)
public class PessoaControllerTest {
	
	@Autowired
    private MockMvc mvc;

    @MockBean
    private IPessoaService pessoaService;

    @Test
    void listar_DeveExibirListaDePessoas() throws Exception {
        when(pessoaService.listarTodos()).thenReturn(Collections.emptyList());

        mvc.perform(get("/pessoas"))
           .andExpect(status().isOk())
           .andExpect(view().name("pessoa-lista"))
           .andExpect(model().attributeExists("pessoas"));
    }

    @Test
    void formNovo_DeveExibirFormulario() throws Exception {
        mvc.perform(get("/pessoas/novo"))
           .andExpect(status().isOk())
           .andExpect(view().name("pessoa-formulario"))
           .andExpect(model().attributeExists("pessoa"));
    }

    @Test
    void formEditar_DeveExibirFormularioComPessoa() throws Exception {
        Pessoa p = new Pessoa(); p.setId(10L);
        when(pessoaService.buscarPorId(10L)).thenReturn(p);

        mvc.perform(get("/pessoas/editar/10"))
           .andExpect(status().isOk())
           .andExpect(view().name("pessoa-formulario"))
           .andExpect(model().attribute("pessoa", p));
    }

    @Test
    void salvar_DeveChamarServicoERedirecionar() throws Exception {
        Pessoa p = new Pessoa(); p.setNome("Joao");
        when(pessoaService.salvar(any(Pessoa.class))).thenReturn(p);

        mvc.perform(post("/pessoas")
                .param("nome", "Joao")
                .param("funcionario", "true")
                .param("gerente", "false"))
           .andExpect(status().is3xxRedirection())
           .andExpect(redirectedUrl("/pessoas"));

        verify(pessoaService).salvar(any(Pessoa.class));
    }

    @Test
    void excluir_DeveChamarServicoERedirecionar() throws Exception {
        mvc.perform(post("/pessoas/excluir/5"))
           .andExpect(status().is3xxRedirection())
           .andExpect(redirectedUrl("/pessoas"));

        verify(pessoaService).excluir(5L);
    }

}
