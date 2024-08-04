package it.ticketplatform.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate; 

@Entity
@Table(name = "Note")
public class Nota {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotNull(message = "Il testo della nota non può essere null")
	@Column(name = "Testo", nullable = false)
	@Size(min = 1, max =500 , message="massimo 500 caratteri")
	private String testo;
	
	@NotNull(message = "La data non può essere null")
	@Column(name = "Data", nullable = false)
	private LocalDate data;
	
	@ManyToOne
	@JoinColumn(name = "UserRif", nullable = false)
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "TicketRif", nullable = false)
	private Ticket ticket;
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getTesto() {
		return testo;
	}

	public void setTesto(String testo) {
		this.testo = testo;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}


	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}
	
}
