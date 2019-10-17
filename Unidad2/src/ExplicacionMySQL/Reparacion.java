package ExplicacionMySQL;

import java.util.Date;

public class Reparacion {
	private int codigo;
	private TipoRep tipo;
	private Coche matricula;
	private Date fecha;
	public Reparacion() {
		super();
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public TipoRep getTipo() {
		return tipo;
	}
	public void setTipo(TipoRep tipo) {
		this.tipo = tipo;
	}
	public Coche getMatricula() {
		return matricula;
	}
	public void setMatricula(Coche matricula) {
		this.matricula = matricula;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
}
