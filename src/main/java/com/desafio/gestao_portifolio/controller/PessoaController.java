package com.desafio.gestao_portifolio.controller;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.desafio.gestao_portifolio.model.Pessoa;
import com.desafio.gestao_portifolio.service.IPessoaService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/pessoas")
@AllArgsConstructor
public class PessoaController {
	
	private final IPessoaService pessoaService;


	@GetMapping
    public String listar(Model model) {
        model.addAttribute("pessoas", pessoaService.listarTodos());
        return "pessoa-lista";
    }

    @GetMapping("/editar/{id}")
    public String form(@PathVariable Long id, Model model) {
        model.addAttribute("pessoa", pessoaService.buscarPorId(id));
        return "pessoa-formulario";
    }

    @PostMapping
    public String salvar(
        @Valid @ModelAttribute("pessoa") Pessoa pessoa,
        BindingResult result,
        Model model,
        RedirectAttributes ra
    ) {
        if (result.hasErrors()) {
            return "pessoa-formulario";
        }

        try {
            pessoaService.salvar(pessoa);
            ra.addFlashAttribute("sucesso", "Pessoa salva com sucesso!");
            return "redirect:/pessoas";
        } catch (DataIntegrityViolationException ex) {
            result.rejectValue("cpf",
                               "duplicate",
                               "Já existe uma pessoa cadastrada com esse CPF");
            return "pessoa-formulario";
        }
    }

    @PostMapping("/excluir/{id}")
    public String excluir(
        @PathVariable Long id,
        RedirectAttributes ra
    ) {
        pessoaService.excluir(id);
        ra.addFlashAttribute("sucesso", "Pessoa excluída com sucesso!");
        return "redirect:/pessoas";
    }
}
