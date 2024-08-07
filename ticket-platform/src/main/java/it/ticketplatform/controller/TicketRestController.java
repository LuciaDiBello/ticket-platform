package it.ticketplatform.controller;

import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.ticketplatform.model.Ticket;
import it.ticketplatform.response.Payload;
import it.ticketplatform.service.TicketService;


@RestController
@CrossOrigin
@RequestMapping("/api")
public class TicketRestController {

	@Autowired
	private TicketService ticketService;
	
	@GetMapping("/show/{id}")
	public ResponseEntity<Payload<Ticket>> get(@PathVariable("id") Integer idTicket) {
		
		  Optional<Ticket> ticket = ticketService.findById(idTicket);
		  if(ticket.isPresent()) {
		  	  return ResponseEntity.ok(new Payload<Ticket>(ticket.get(), null, HttpStatus.OK));
		  } else {
			  return new ResponseEntity<Payload<Ticket>>(
					 new Payload<Ticket>(null, "Ticket con id " + idTicket + " non trovato", HttpStatus.NOT_FOUND), 
					 HttpStatus.NOT_FOUND);
		          }
	   }

	
	  @GetMapping("/lista-ticket")
	    public ResponseEntity<Payload<List<Ticket>>> getAll() {
		  
	        List<Ticket> ticketList = ticketService.findAll();
	        return ResponseEntity.ok(new Payload<List<Ticket>>(ticketList, null, HttpStatus.OK));
	    }
	
	  @GetMapping("/ticket-categoria/{categoriaTipologia}")
	    public ResponseEntity<Payload<List<Ticket>>> getByCategoriaTipologia(@PathVariable("categoriaTipologia") String categoriaTipologia) {
		  
	        List<Ticket> tickets = ticketService.findByCategoriaTipologia(categoriaTipologia);
	        if (tickets.isEmpty()) {
	            return new ResponseEntity<>(
	                   new Payload<>(null, "Nessun ticket trovato per la categoria " + categoriaTipologia, HttpStatus.NOT_FOUND),
	             HttpStatus.NOT_FOUND);
	         }
	        return ResponseEntity.ok(new Payload<>(tickets, null, HttpStatus.OK));
	    }
	  
	  
	    @GetMapping("/ticket-stato/{stato}")
	    public ResponseEntity<Payload<List<Ticket>>> getByStato(@PathVariable("stato") String stato) {
	    	
	        List<Ticket> tickets = ticketService.findByStato(stato);
	        if (tickets.isEmpty()) {
	            return new ResponseEntity<>(
	                    new Payload<>(null, "Nessun ticket trovato per lo stato " + stato, HttpStatus.NOT_FOUND),
	                    HttpStatus.NOT_FOUND);
	         }
	         return ResponseEntity.ok(new Payload<>(tickets, null, HttpStatus.OK));
	    }
	}
	
	
	