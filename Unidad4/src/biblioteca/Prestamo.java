package biblioteca;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@Table
public class Prestamo  implements Serializable{
	@EmbeddedId
	private PrestamoClave id;
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date fechaDevolPrevista;
	@Column
	@Temporal(TemporalType.DATE)
	private Date fechaDevolReal;
	public Prestamo() {
		super();
	}
	public Prestamo(PrestamoClave id, Date fechaDevolPrevista, Date fechaDevolReal) {
		super();
		this.id = id;
		this.fechaDevolPrevista = fechaDevolPrevista;
		this.fechaDevolReal = fechaDevolReal;
	}
	public PrestamoClave getId() {
		return id;
	}
	public void setId(PrestamoClave id) {
		this.id = id;
	}
	public Date getFechaDevolPrevista() {
		return fechaDevolPrevista;
	}
	public void setFechaDevolPrevista(Date fechaDevolPrevista) {
		this.fechaDevolPrevista = fechaDevolPrevista;
	}
	public Date getFechaDevolReal() {
		return fechaDevolReal;
	}
	public void setFechaDevolReal(Date fechaDevolReal) {
		this.fechaDevolReal = fechaDevolReal;
	}
	
	public void mostrar() {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println("ISBN:"+id.getLibro().getIsbn()+
				"\tSocio:"+id.getSocio().getNif()+
				"\tFecha Pr�stamo:"+formato.format(id.getFechaP())+
				"\tFecha Prevista Devol:"+formato.format(fechaDevolPrevista)+
				"\tFecha Real Devol:"+formato.format(fechaDevolReal));
	}
}
