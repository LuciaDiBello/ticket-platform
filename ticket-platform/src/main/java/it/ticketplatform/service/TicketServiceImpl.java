package it.ticketplatform.service;

import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.ticketplatform.model.Ticket;
import it.ticketplatform.repository.TicketRepository;

@Service
public class TicketServiceImpl implements TicketService {

	@Autowired
	private TicketRepository ticketRepository;
	
	@Override
	public Optional<Ticket> findById(Integer id) {
		return ticketRepository.findById(id);
	}
	
	@Override
	public List<Ticket> findAll() {
	     return ticketRepository.findAll();
	 }

	@Override
	public List<Ticket> findByCategoriaTipologia(String categoriaTipologia) {
	  return ticketRepository.findByCategoriaTipologia(categoriaTipologia);
	  }
	
	@Override
	public List<Ticket> findByStato(String stato) {
	   return ticketRepository.findByStato(stato);
	  }
}
