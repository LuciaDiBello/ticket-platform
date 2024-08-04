package it.ticketplatform.service;

import java.util.Optional;
import java.util.List;

import it.ticketplatform.model.Ticket;

public interface TicketService {

    public Optional<Ticket> findById(Integer id);
	
    public List<Ticket> findAll();
    
    public List<Ticket> findByCategoriaTipologia(String categoriaTipologia);
   
    public List<Ticket> findByStato(String stato);

}
