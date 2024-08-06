package it.ticketplatform.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.JoinColumn;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List; 
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Ticket")
public class Ticket {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull(message = "Il titolo non può essere null")
	@NotBlank(message = "Il titolo non può essere blank ")
	@Column(name = "Titolo", nullable = false)
	private String name;

	@NotNull(message = "Il testo del ticket non può essere null")
	@NotBlank(message = "Il testo del ticket non può essere blank ")
	@Column(name = "Testo", nullable = false)
	@Size(min = 1, max =500 , message="massimo 500 caratteri")
	private String testo;
	
	@NotNull(message = "La data non può essere null")
	@Column(name = "Data", nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate data;
	
	@NotNull(message = "Lo stato non può essere vuoto")
	@Column(name = "Stato", nullable = false)
	private String stato;
	
	@ManyToOne
	@JoinColumn(name = "UserRif", nullable = false)
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "CategoriaRif", nullable = false)
	private Categoria categoria;
	
	@OneToMany(mappedBy = "ticket")
	private List<Nota> note;
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}
	
	public String getTesto() {
		return testo;
	}

	public void setTesto(String testo) {
		this.testo = testo;
	}
	
	public String getStato() {
		return stato;
	}
	
	public void setStato(String stato) {
		this.stato = stato;
	}
    
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	public List<Nota> getNote() {
		return note;
	}

	public void setNote(List<Nota> note) {
		this.note = note;
	}

}

