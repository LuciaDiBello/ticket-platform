package it.ticketplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.ticketplatform.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
	
}
