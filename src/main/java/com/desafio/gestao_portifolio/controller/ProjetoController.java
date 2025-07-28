package com.desafio.gestao_portifolio.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.desafio.gestao_portifolio.model.Pessoa;
import com.desafio.gestao_portifolio.model.Projeto;
import com.desafio.gestao_portifolio.model.enums.NivelRisco;
import com.desafio.gestao_portifolio.model.enums.StatusProjeto;
import com.desafio.gestao_portifolio.service.IPessoaService;
import com.desafio.gestao_portifolio.service.IProjetoService;
import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/projetos")
@AllArgsConstructor
public class ProjetoController {

    private final IProjetoService projetoService;
    private final IPessoaService pessoaService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("projetos", projetoService.listarTodos());
        return "projeto-lista";
    }


    @GetMapping({"/novo","/editar/{id}"})
    public String formulario(
        @PathVariable(required = false) Long id,
        Model model
    ) {
        Projeto projeto = (id == null)
            ? new Projeto()
            : projetoService.buscarPorId(id);

        model.addAttribute("projeto", projeto);
        model.addAttribute("gerentes",
            pessoaService.listarTodos().stream()
                .filter(Pessoa::isGerente)
                .toList()
        );
        model.addAttribute("statusList", StatusProjeto.values());
        model.addAttribute("nivelRiscoList", NivelRisco.values());
        model.addAttribute("membrosPossiveis",
            pessoaService.listarTodos().stream()
                .filter(Pessoa::isFuncionario)
                .toList()
        );
        return "projeto-formulario";
    }
    
    
    @PostMapping
    public String salvar(
        @ModelAttribute Projeto projetoForm,
        @RequestParam(value="membrosId", required=false) List<Long> membrosId,
        RedirectAttributes ra
    ) {
        projetoService.salvar(projetoForm, membrosId);
        ra.addFlashAttribute("sucesso", "Projeto salvo com sucesso!");
        return "redirect:/projetos";
    }

    @PostMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id, RedirectAttributes ra) {
        try {
        	projetoService.excluir(id);
            ra.addFlashAttribute("sucesso", "Projeto excluído com sucesso.");
        } catch (IllegalStateException e) {
            ra.addFlashAttribute("erro", e.getMessage());
        }
        return "redirect:/projetos";
    }
}