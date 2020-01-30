package com.example.algamoney.api.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.algamoney.api.model.Categoria;

/*
 * Essa classe é uma interface
 * Que extend a JpaRepository e com isso, essa interface vai ganhar
 * vários métodos de consulta, salvar, deletar ou atualizar o BD
 */

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

}
