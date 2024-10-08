package it.ticketplatform.repository;

import java.util.List;
import java.util.Optional;
import it.ticketplatform.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);
	
    List<User> findByDisponibileTrue();
}















