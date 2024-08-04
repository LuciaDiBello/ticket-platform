package it.ticketplatform.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.validation.BindingResult;
import jakarta.validation.Valid;

import it.ticketplatform.model.Categoria;
import it.ticketplatform.model.Ticket;
import it.ticketplatform.model.User;
import it.ticketplatform.repository.CategoriaRepository;
import it.ticketplatform.repository.TicketRepository;
import it.ticketplatform.repository.UserRepository;

@Controller
public class TicketController {

	@Autowired
	private TicketRepository repositoryTicket;
	
	@Autowired
	private UserRepository repositoryUser;
	
	@Autowired
	private CategoriaRepository repositoryCategoria;
		 
	 @GetMapping("/lista-admin")
	 public String listaAdmin(Model model) {
	
		List<Ticket> listaTicket = repositoryTicket.findAll();
		model.addAttribute("list", listaTicket);
		return "lista-admin";
	}
	 
	 @GetMapping("/ticket-search")
	 public String ticketByName(Model model, @RequestParam(name = "name", required = false) String name) {
	
		    List<Ticket> listaTicket = repositoryTicket.findByName(name);
			model.addAttribute("list", listaTicket);
			return "redirect:/show/" + listaTicket.get(0).getId();
	}
	 
	 
	@GetMapping("/show/{id}")
	public String dettaglioTicket(@PathVariable("id") int ticketId, Model model) {
		
		model.addAttribute("ticket", repositoryTicket.getReferenceById(ticketId));
	
		return "dettaglioTicket";
	  }
	
	
	@GetMapping("/insert-ticket")
	public String aggiungiTicket(Model model) {
		
		List<Categoria> listCategorie = repositoryCategoria.findAll();  
		List<User> listUsersDisponibili = repositoryUser.findByDisponibileTrue();
		Ticket ticket = new Ticket();
		ticket.setStato("DaFare");
		
	    model.addAttribute("usersForm", listUsersDisponibili);
	    model.addAttribute("categorieForm", listCategorie);
	    model.addAttribute("ticketForm", ticket);
	    
	    return "insert-ticket"; 
	}
	
	
	@PostMapping("/insert-ticket")
	public String storeTicket(@Valid @ModelAttribute("ticketForm") Ticket ticketForm, BindingResult bindingResult, Model model){
		
	   if(bindingResult.hasErrors()) {
		   List<User> listUsersDisponibili = repositoryUser.findByDisponibileTrue();
		   model.addAttribute("categorieForm", repositoryCategoria.findAll());
		   model.addAttribute("usersForm", listUsersDisponibili);
		  
	      return "insert-ticket";
	   }
	    
	  repositoryTicket.save(ticketForm);
	  
	  return "redirect:/lista-admin";
	}

	
	@GetMapping("/update-ticket/{id}")
	public String edit(@PathVariable("id") Integer id, Model model) {
		
		List<Categoria> listCategorie = repositoryCategoria.findAll();  
		List<User> listUsersDisponibili = repositoryUser.findByDisponibileTrue();
	
	    model.addAttribute("usersForm", listUsersDisponibili);
	    model.addAttribute("categorieForm", listCategorie);
		model.addAttribute("ticketForm", repositoryTicket.findById(id).get());
		
		return "update-ticket";
	}
	
	
	@PostMapping("/update-ticket/{id}")
	public String updateTicket(@Valid @ModelAttribute("ticketForm") Ticket ticketForm, BindingResult bindingResult, Model model) {
		
		 if(bindingResult.hasErrors()) {
			List<User> listUsersDisponibili = repositoryUser.findByDisponibileTrue();
			model.addAttribute("categorieForm", repositoryCategoria.findAll());
			model.addAttribute("usersForm", listUsersDisponibili);
		    return "update-ticket";
		  }
		   
		repositoryTicket.save(ticketForm);
		
		return "redirect:/lista-admin";
	}
	
	
	@PostMapping("/delete/{id}")
	public String deleteTicket(@PathVariable("id") Integer id) {
		
		repositoryTicket.deleteById(id);
		
		return "redirect:/lista-admin";
	}
	
	
	@GetMapping("/update-state/{id}")
	public String updateState(@PathVariable("id") int ticketId, Model model) {
		
		model.addAttribute("ticketForm", repositoryTicket.getReferenceById(ticketId));
	
		return "update-state";
	  }
	
	
	@PostMapping("/update-state/{id}")
	public String updateState( @Valid @ModelAttribute("ticketForm") Ticket ticketForm, BindingResult bindingResult, Model model) {
		
		 if(bindingResult.hasErrors()) {
		  
		      return "update-state";
		   }
		  
		repositoryTicket.save(ticketForm);
		
		return "redirect:/user-page/" + ticketForm.getUser().getId();
	}

	
}
	

