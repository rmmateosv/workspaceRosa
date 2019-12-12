package biblioteca;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="libro")
public class Libro  implements Serializable{
	@Column(name="isbn",nullable = false)
	@Id
	private String isbn;
	@Column(nullable = false)
	private int numEjemplares;
	@Column(nullable = false)
	private String titulo;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "id.libro")
	private List<Prestamo> prestamos = new ArrayList<Prestamo>();
	public Libro() {
		super();
	}
	public Libro(String isbn, int numEjemplares, String titulo, List<Prestamo> prestamos) {
		super();
		this.isbn = isbn;
		this.numEjemplares = numEjemplares;
		this.titulo = titulo;
		this.prestamos = prestamos;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public int getNumEjemplares() {
		return numEjemplares;
	}
	public void setNumEjemplares(int numEjemplares) {
		this.numEjemplares = numEjemplares;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public List<Prestamo> getPrestamos() {
		return prestamos;
	}
	public void setPrestamos(List<Prestamo> prestamos) {
		this.prestamos = prestamos;
	}
	public void mostrar(boolean mostrarP) {
		System.out.println("Isbn:"+isbn + 
				"\tNumEjemplares:"+numEjemplares+
				"\tTitulo:"+titulo);
		if(mostrarP) {
			System.out.println("Préstamos del libro");
			for(Prestamo p:prestamos) {
				p.mostrar();
			}
		}
		
	}
}
