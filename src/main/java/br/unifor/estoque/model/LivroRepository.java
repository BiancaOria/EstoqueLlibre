package br.unifor.estoque.model;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LivroRepository extends JpaRepository<Livro, String> {


    List<Livro> findByAutor(String autor);

    List<Livro> findByTitulo(String titulo);

    List<Livro> findByAno(int ano);

    List<Livro> findByGenero(String genero);

    Optional<Livro> findById(Long id);

    List<Livro> findAll();



}
