package com.example.algamoney.api.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.algamoney.api.model.Categoria;

/*
 * Essa classe é uma interface
 * ****************************
 * diferença entre classe e interface https://www.devmedia.com.br/interfaces-x-classes-abstratas/13337
 * ****************************
 * Que extend a JpaRepository e com isso, essa interface vai ganhar
 * vários métodos de consulta, salvar, deletar ou atualizar o BD
 */

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

}
