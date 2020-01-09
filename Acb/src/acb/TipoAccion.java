package acb;

import java.util.ArrayList;

public class TipoAccion {
	private String tipo;
	private String descripcion;
	
	private ArrayList<Accion> acciones = new ArrayList<Accion>();
	
	public TipoAccion() {
		super();
	}
	public TipoAccion(String tipo, String descripcion) {
		super();
		this.tipo = tipo;
		this.descripcion = descripcion;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public void mostrar() {
		System.out.println("Tipo Acción:"+tipo+" " + descripcion );
	}
	
	public ArrayList<Accion> getAcciones() {
		return acciones;
	}
	public void setAcciones(ArrayList<Accion> acciones) {
		this.acciones = acciones;
	}
	
}
