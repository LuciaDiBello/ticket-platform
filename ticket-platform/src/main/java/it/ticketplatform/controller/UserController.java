package it.ticketplatform.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.ticketplatform.model.Ticket;
import it.ticketplatform.model.User;
import it.ticketplatform.repository.UserRepository;
import jakarta.validation.Valid;

@Controller
public class UserController {
	
	@Autowired
	private UserRepository repositoryUser;
	
	
	@GetMapping("/user-page/{id}")
	public String dettaglioTicket(@PathVariable("id") int userId, Model model) {
		
		model.addAttribute("user", repositoryUser.getReferenceById(userId));
	
		return "user-page";
	  }
	
	@GetMapping("/update-user/{id}")
	public String updateUser(@PathVariable("id") Integer id, Model model) {
		
		User userForm = repositoryUser.findById(id).get();
		
	    int l = userForm.getTickets().size();			
	    boolean disp = false; 
	    String err = null;
	    int i=0; 
	    while (userForm.getTickets().get(i).getStato() == "Completato" && i < l) i++;
		if (i == l) disp= true;
	    else err = "Stato attivo, non è possibile impostare il campo a true";
		
		if (disp == false)  model.addAttribute("error", err);
		
		model.addAttribute("formUser", userForm);
		
		return "update-user";
	}
	
	
	@PostMapping("/update-user/{id}")
	public String updateUser( @Valid @ModelAttribute("formUser") User formUser, BindingResult bindingResult, Model model) {
		
		if(bindingResult.hasErrors()) {
			return "update-user";
		}
		
		repositoryUser.save(formUser);
		
		return "redirect:/user-page/" + formUser.getId();
	}

}


