package br.unifor.estoque.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
public class Estoque {

    @Autowired
    private LivroRepository livroRepository;

    private final WebClient.Builder webClientBuilder;

    @Autowired
    public Estoque(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    // Método que consome a API externa para buscar informações de um livro
    public String consultarLivroNaApiExterna(String titulo) {
        String url = "https://api.exemplo.com/livro?titulo=" + titulo;

        return webClientBuilder.baseUrl(url)
                .build()
                .get()
                .retrieve()
                .bodyToMono(String.class)
                .block();  // Para bloquear e retornar a resposta de forma síncrona
    }

    public List<Livro> buscarPorAutor(String autor) {
        return livroRepository.findByAutor(autor);
    }

    public List<Livro> buscarTodos() {
        return livroRepository.findAll();
    }

    public List<Livro> buscarPorTitulo(String titulo) {
        return livroRepository.findByTitulo(titulo);
    }

    public List<Livro> buscarPorAno(int ano) {
        return livroRepository.findByAno(ano);
    }

    public List<Livro> buscarPorGenero(String genero) {
        return livroRepository.findByGenero(genero);
    }

    public void addLivro(Livro livro) {
        livroRepository.save(livro);
        System.out.println("Livro " + livro.getTitulo() + " adicionado ao estoque.");
    }

    public void removerLivro(Long id) {
        Livro livroEncontrado = livroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));

        livroRepository.delete(livroEncontrado);  // Remover livro
        System.out.println("Livro " + livroEncontrado.getTitulo() + " removido do estoque.");
    }

    public void darBaixa(Long id) {
        Livro livroEncontrado = livroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));

        if (livroEncontrado.getQuantidade() > 0) {
            livroEncontrado.setQuantidade(livroEncontrado.getQuantidade() - 1);
            livroRepository.save(livroEncontrado);  // Salvar a alteração
            System.out.println("Livro " + livroEncontrado.getTitulo() + " vendido.");
        } else {
            throw new RuntimeException("Quantidade insuficiente para o livro " + livroEncontrado.getTitulo());
        }
    }
}
