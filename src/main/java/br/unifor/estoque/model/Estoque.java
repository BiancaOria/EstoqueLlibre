package br.unifor.estoque.model;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

@Component
public class Estoque {


    private LivroRepository livroRepository;

    public ArrayList<Livro> buscarPorAutor(String autor) {
        return livroRepository.findByAutor(autor);
    }

    public ArrayList<Livro> buscarPorTitulo(String titulo) {
        return livroRepository.findByTitulo(titulo);
    }

    public ArrayList<Livro> buscarPorAno(int ano) {
        return livroRepository.findByAno(ano);
    }

    public ArrayList<Livro> buscarPorGenero(String genero) {
        return livroRepository.findByGenero(genero);
    }

    public void addLivro(Livro livro) {
        livroRepository.save(livro);
        System.out.println("Livro " + livro.getTitulo() + " adicionado ao estoque.");
    }

    public void removerLivro(Livro livro) {
        Livro livroEncontrado;
        livroEncontrado = livroRepository.findById(livro.getId())
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));

        livroRepository.delete(livro);
        System.out.println("Livro " + livro.getTitulo() + " removido do estoque.");
    }

    public void darBaixa(Livro livro) {
        Livro livroEncontrado = livroRepository.findById(livro.getId())
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));

        if (livroEncontrado.getQuantidade() > 0) {
            livroEncontrado.setQuantidade(livroEncontrado.getQuantidade() - 1);
            livroRepository.save(livroEncontrado);
            System.out.println("Livro " + livroEncontrado.getTitulo() + " vendido.");
        } else {
            throw new RuntimeException("Quantidade insuficiente para o livro " + livroEncontrado.getTitulo());
        }
    }
}
