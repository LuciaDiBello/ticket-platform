package it.ticketplatform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import jakarta.validation.Valid;
import it.ticketplatform.model.Nota;
import it.ticketplatform.model.Ticket;
import it.ticketplatform.model.User;
import it.ticketplatform.repository.NotaRepository;
import it.ticketplatform.repository.TicketRepository;

@Controller
public class NotaController {

	@Autowired
	private NotaRepository repositNota;
	  
	@Autowired
	private TicketRepository repositTicket;
	
	@GetMapping("/insert-nota/{id}")
	public String insertNota(@PathVariable("id") Integer id, Model model) {
			
		Ticket ticket = repositTicket.findById(id).get();
		User user = ticket.getUser();  //Individuare lo user corrente, aggiungere l'autenticazione
		Nota notaForm = new Nota();
		notaForm.setTicket(ticket);
		notaForm.setUser(user);
			
		model.addAttribute("notaForm", notaForm);
		
		return "insert-nota";
	}


	@PostMapping("/insert-nota")
	public String storeNota(@Valid @ModelAttribute("notaForm") Nota notaForm, BindingResult bindingResult, Model model) {
	
		if(bindingResult.hasErrors()) {
			return "insert-nota";
		}
		
		repositNota.save(notaForm);
		return "redirect:/show/" + notaForm.getTicket().getId();
	}
	
}

