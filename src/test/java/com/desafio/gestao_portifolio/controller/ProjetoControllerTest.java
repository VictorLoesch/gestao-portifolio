package com.desafio.gestao_portifolio.controller;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.desafio.gestao_portifolio.model.Projeto;
import com.desafio.gestao_portifolio.model.enums.NivelRisco;
import com.desafio.gestao_portifolio.model.enums.StatusProjeto;
import com.desafio.gestao_portifolio.service.IPessoaService;
import com.desafio.gestao_portifolio.service.IProjetoService;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(ProjetoController.class)
public class ProjetoControllerTest {
	
	@Autowired
    private MockMvc mvc;

    @MockBean
    private IProjetoService projetoService;

    @MockBean
    private IPessoaService pessoaService;

    @Test
    void listar_DeveExibirLista() throws Exception {
        when(projetoService.listarTodos()).thenReturn(Collections.emptyList());

        mvc.perform(get("/projetos"))
           .andExpect(status().isOk())
           .andExpect(view().name("projeto-lista"))
           .andExpect(model().attributeExists("projetos"));
    }

    @Test
    void formNovo_DeveExibirFormularioCompleto() throws Exception {
        when(pessoaService.listarTodos()).thenReturn(Collections.emptyList());

        mvc.perform(get("/projetos/novo"))
           .andExpect(status().isOk())
           .andExpect(view().name("projeto-formulario"))
           .andExpect(model().attributeExists("projeto"))
           .andExpect(model().attributeExists("gerentes"))
           .andExpect(model().attributeExists("statusList"))
           .andExpect(model().attributeExists("nivelRiscoList"))
           .andExpect(model().attributeExists("membrosPossiveis"));
    }

    @Test
    void salvar_DeveChamarServicoERedirecionar() throws Exception {
        when(projetoService.salvar(any(Projeto.class), anyList())).thenReturn(new Projeto());

        mvc.perform(post("/projetos")
                .param("nome", "X")
                .param("gerente.id", "1")
                .param("status", StatusProjeto.PLANEJADO.name())
                .param("nivelRisco", NivelRisco.BAIXO.name())
                .param("membrosId", "1", "2"))
           .andExpect(status().is3xxRedirection())
           .andExpect(redirectedUrl("/projetos"));

        verify(projetoService).salvar(any(Projeto.class), anyList());
    }

    @Test
    void excluir_DeveChamarServicoERedirecionar() throws Exception {
        mvc.perform(post("/projetos/excluir/3"))
           .andExpect(status().is3xxRedirection())
           .andExpect(redirectedUrl("/projetos"));

        verify(projetoService).excluir(3L);
    }

}
