package it.ticketplatform.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="User")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="username", nullable=false)
	@NotNull(message = "Lo username non può essere null")
	private String username;
	
	@Column(name="password", nullable=false)
	@NotNull(message = "La password non può essere null")
	private String password;
	
	@Column(name="email", nullable=true)
	private String email;
	
	@Column(name="disponibile", nullable=false)
	@NotNull (message = "disponibile non può essere null")
	private boolean disponibile;
	
	@OneToMany(mappedBy = "user")
	private List<Ticket> tickets;
	
	@OneToMany(mappedBy = "user")
	private List<Nota> note;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Role> roles;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	public boolean getDisponibile() {
		return disponibile;
	}

	public void setBusy(boolean busy) {
		this.disponibile = disponibile;
	}
	
	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	
	public List<Nota> getNote() {
		return note;
	}

	public void setNote(List<Nota> note) {
		this.note = note;
	}
	
	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
}
