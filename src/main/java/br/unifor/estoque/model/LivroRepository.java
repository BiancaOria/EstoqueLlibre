package br.unifor.estoque.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    ArrayList<Livro> findByAutor(String autor);

    ArrayList<Livro> findByTitulo(String titulo);

    ArrayList<Livro> findByAno(int ano);

    ArrayList<Livro> findByGenero(String genero);
}
