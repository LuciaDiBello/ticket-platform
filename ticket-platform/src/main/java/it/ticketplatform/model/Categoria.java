package it.ticketplatform.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


@Entity
@Table(name="Categorie")
public class Categoria {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="tipologia", nullable=false)
	@NotNull(message="tipologia cannot be null")
	@NotBlank(message="tipologia cannot be null")
	private String tipologia;
	
	@OneToMany(mappedBy = "categoria")
	private List<Ticket> tickets;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTipologia() {
		return tipologia;
	}

	public void setTipologia(String tipologia) {
		this.tipologia = tipologia;
	}

	
	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTicket(List<Ticket> tickets) {
		this.tickets = tickets;
	}
	
}


