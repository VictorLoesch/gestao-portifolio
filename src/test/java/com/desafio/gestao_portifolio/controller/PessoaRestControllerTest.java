package com.desafio.gestao_portifolio.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.desafio.gestao_portifolio.model.Pessoa;
import com.desafio.gestao_portifolio.service.impl.PessoaServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(PessoaRestController.class)
public class PessoaRestControllerTest {
	
	@Autowired
    private MockMvc mvc;

    @MockBean
    private PessoaServiceImpl pessoaService;

    @Autowired
    private ObjectMapper mapper;

    @Test
    void salvarPessoa_DeveRetornarJson() throws Exception {
        Pessoa p = new Pessoa();
        p.setId(1L);
        p.setNome("Maria");
        p.setFuncionario(true);
        p.setGerente(false);

        when(pessoaService.salvar(any(Pessoa.class))).thenReturn(p);

        mvc.perform(post("/api/pessoas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(p)))
           .andExpect(status().isOk())
           .andExpect(content().contentType(MediaType.APPLICATION_JSON))
           .andExpect(jsonPath("$.id").value(1))
           .andExpect(jsonPath("$.nome").value("Maria"));
    }

}
