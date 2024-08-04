package it.ticketplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.ticketplatform.model.Nota;

public interface NotaRepository extends JpaRepository<Nota, Integer> {

}
