package it.ticketplatform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.ticketplatform.model.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
	
	List<Ticket> findByName(String name);
	
	List<Ticket> findByStato(String stato);
	
	@Query("SELECT t FROM Ticket t WHERE t.categoria.tipologia = :categoriaTipologia")
    	List<Ticket> findByCategoriaTipologia(@Param("categoriaTipologia") String categoriaTipologia);
}



