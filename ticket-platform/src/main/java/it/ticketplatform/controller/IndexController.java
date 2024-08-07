package it.ticketplatform.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import it.ticketplatform.repository.UserRepository;
import it.ticketplatform.model.User;

@Controller
public class IndexController {
	
      @Autowired
      private UserRepository repositoryUser;

      @GetMapping("/index")
      public String indexPage(Model model) {
    	
    	 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
         String username = authentication.getName();
         Optional<User> userLoggato = repositoryUser.findByUsername(username);
         User userForm = userLoggato.get();
         
         model.addAttribute("userForm", userForm);
    	
        return "index";
    }

}



