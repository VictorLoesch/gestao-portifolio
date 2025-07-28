package com.desafio.gestao_portifolio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;

import com.desafio.gestao_portifolio.model.Pessoa;
import com.desafio.gestao_portifolio.service.IPessoaService;

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

    @GetMapping({"/novo","/editar/{id}"})
    public String form(@PathVariable(required=false) Long id, Model model, RedirectAttributes ra) {
        Pessoa p = (id == null ? new Pessoa() : pessoaService.buscarPorId(id));
        model.addAttribute("pessoa", p);
        ra.addFlashAttribute("sucesso", "Pessoa excluída com sucesso!");
        return "pessoa-formulario";
    }

    @PostMapping
    public String salvar(@ModelAttribute Pessoa pessoa) {
        pessoaService.salvar(pessoa);
        return "redirect:/pessoas";
    }


    @PostMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id, RedirectAttributes ra) {
        pessoaService.excluir(id);
        ra.addFlashAttribute("sucesso", "Pessoa excluída com sucesso!");
        return "redirect:/pessoas";
    }
}
