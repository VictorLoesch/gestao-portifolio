package com.desafio.gestao_portifolio.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.desafio.gestao_portifolio.model.Pessoa;
import com.desafio.gestao_portifolio.service.impl.PessoaServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/pessoas")
@AllArgsConstructor
public class PessoaRestController {
	
	private final PessoaServiceImpl pessoaService;
	
	
	@Operation(
	      summary = "Cria uma nova pessoa",
	      description = "Recebe o corpo JSON com dados de Pessoa e devolve o objeto persistido",
	      responses = {
	        @ApiResponse(responseCode = "201", description = "Pessoa criada com sucesso"),
	        @ApiResponse(responseCode = "400", description = "Dados inválidos")
	      }
	)
	@PostMapping
	private Pessoa salvarPessoa(@Valid @RequestBody Pessoa pessoa) {
		return pessoaService.salvar(pessoa);
	}
}
