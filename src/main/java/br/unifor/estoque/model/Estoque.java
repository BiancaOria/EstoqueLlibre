package br.unifor.estoque.model;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class Estoque {

    @Autowired
    private LivroRepository livroRepository;

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
