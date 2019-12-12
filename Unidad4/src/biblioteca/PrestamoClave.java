package biblioteca;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class PrestamoClave implements Serializable{
	@ManyToOne
	@JoinColumn(columnDefinition = "isbn")
	private Libro libro;
	@ManyToOne
	@JoinColumn(columnDefinition = "id")
	private Socio socio;
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date fechaP;
	public PrestamoClave() {
		super();
	}
	public PrestamoClave(Libro libro, Socio socio, Date fechaP) {
		super();
		this.libro = libro;
		this.socio = socio;
		this.fechaP = fechaP;
	}
	public Libro getLibro() {
		return libro;
	}
	public void setLibro(Libro libro) {
		this.libro = libro;
	}
	public Socio getSocio() {
		return socio;
	}
	public void setSocio(Socio socio) {
		this.socio = socio;
	}
	public Date getFechaP() {
		return fechaP;
	}
	public void setFechaP(Date fechaP) {
		this.fechaP = fechaP;
	}
	
	
}
