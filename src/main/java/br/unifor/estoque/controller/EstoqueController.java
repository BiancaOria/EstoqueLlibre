package br.unifor.estoque.controller;

import br.unifor.estoque.model.Estoque;
import br.unifor.estoque.model.Livro;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

@RestController
@RequestMapping("/estoque")
public class EstoqueController {

    @Autowired
    private Estoque estoqueService;

    @GetMapping("/")
    public String home() {
        return "Bem-vindo à aplicação!";
    }

    @GetMapping("/autor/{autor}")
    public ArrayList<Livro> buscarPorAutor(@PathVariable String autor) {
        return estoqueService.buscarPorAutor(autor);
    }

    @GetMapping("/titulo/{titulo}")
    public ArrayList<Livro> buscarPorTitulo(@PathVariable String titulo) {
        return estoqueService.buscarPorTitulo(titulo);
    }

    @GetMapping("/ano/{ano}")
    public ArrayList<Livro> buscarPorAno(@PathVariable int ano) {
        return estoqueService.buscarPorAno(ano);
    }

    @GetMapping("/genero/{genero}")
    public ArrayList<Livro> buscarPorGenero(@PathVariable String genero) {
        return estoqueService.buscarPorGenero(genero);
    }

    @PostMapping("/add")
    public void addLivro(@RequestBody @Valid Livro livro) {
        estoqueService.addLivro(livro);
    }

    @DeleteMapping("/remover")
    public void removerLivro(@RequestBody Livro livro) {
        estoqueService.removerLivro(livro);
    }

    @PostMapping("/vender")
    public void darBaixa(@RequestBody Livro livro) {
        estoqueService.darBaixa(livro);
    }
}

