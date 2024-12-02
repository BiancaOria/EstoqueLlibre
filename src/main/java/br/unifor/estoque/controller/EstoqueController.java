package br.unifor.estoque.controller;

import br.unifor.estoque.model.Estoque;
import br.unifor.estoque.model.Livro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<Livro> buscarPorAutor(@PathVariable String autor) {
        return estoqueService.buscarPorAutor(autor);
    }

    @GetMapping("/titulo/{titulo}")
    public List<Livro> buscarPorTitulo(@PathVariable String titulo) {
        // Chama o método para consultar a API externa
        String respostaApi = estoqueService.consultarLivroNaApiExterna(titulo);
        System.out.println("Resposta da API Externa: " + respostaApi);

        // Se necessário, use a resposta da API para retornar algo para o usuário
        // Aqui estou apenas imprimindo no console a resposta da API externa
        return estoqueService.buscarPorTitulo(titulo);
    }

    @GetMapping("/ano/{ano}")
    public List<Livro> buscarPorAno(@PathVariable int ano) {
        return estoqueService.buscarPorAno(ano);
    }

    @GetMapping("/todos")
    public List<Livro> buscarTodos() {
        return estoqueService.buscarTodos();
    }

    @GetMapping("/genero/{genero}")
    public List<Livro> buscarPorGenero(@PathVariable String genero) {
        return estoqueService.buscarPorGenero(genero);
    }

    @PostMapping("/add")
    public String addLivro(@RequestBody Livro livro) {
        estoqueService.addLivro(livro);
        return "Livro adicionado ao estoque!";
    }

    @DeleteMapping("/remover/{id}")
    public void removerLivro(@PathVariable Long id) {
        estoqueService.removerLivro(id);
    }

    @PostMapping("/vender/{id}")
    public void darBaixa(@PathVariable Long id) {
        estoqueService.darBaixa(id);
    }
}
