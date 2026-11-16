package br.umc.crud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.umc.crud.model.Produto;
import br.umc.crud.service.ProdutoService;
import jakarta.validation.Valid;

@Controller
public class ProdutoController {

    private final ProdutoService service;

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String inicio() {
        return "redirect:/produtos";
    }

    // LISTAR (com busca opcional)
    @GetMapping("/produtos")
    public String listar(@RequestParam(required = false) String busca, Model model) {
        model.addAttribute("produtos", service.listar(busca));
        model.addAttribute("busca", busca);
        return "lista";
    }

    // NOVO
    @GetMapping("/produtos/novo")
    public String novo(Model model) {
        model.addAttribute("produto", new Produto());
        return "form";
    }

    // SALVAR (cria ou atualiza) com validacao e redirect
    @PostMapping("/produtos/salvar")
    public String salvar(@Valid @ModelAttribute("produto") Produto produto, BindingResult result) {
        if (result.hasErrors()) {
            return "form";
        }
        service.salvar(produto);
        return "redirect:/produtos";
    }

    // EDITAR
    @GetMapping("/produtos/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        return service.buscarPorId(id)
                .map(p -> {
                    model.addAttribute("produto", p);
                    return "form";
                })
                .orElse("redirect:/produtos");
    }

    // EXCLUIR
    @GetMapping("/produtos/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        service.excluir(id);
        return "redirect:/produtos";
    }
}
