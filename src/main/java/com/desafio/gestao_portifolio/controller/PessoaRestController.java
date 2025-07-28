package com.desafio.gestao_portifolio.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.gestao_portifolio.model.Pessoa;
import com.desafio.gestao_portifolio.service.impl.PessoaServiceImpl;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/pessoas")
@AllArgsConstructor
public class PessoaRestController {
	
	private final PessoaServiceImpl pessoaService;
	
	@PostMapping
	private Pessoa salvarPessoa(@RequestBody Pessoa pessoa) {
		return pessoaService.salvar(pessoa);
	}
}
