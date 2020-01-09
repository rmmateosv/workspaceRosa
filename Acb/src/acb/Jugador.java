package acb;

import java.util.ArrayList;

public class Jugador {
	private int codigo;
	private Equipo equipo;
	private int dorsal;
	private String nombre;
	private String tipo;
	
	private ArrayList<Accion> acciones = new ArrayList<Accion>();
	
	public Jugador() {
		super();
	}
	public Jugador(int codigo, Equipo equipo, int dorsal, String nombre, String tipo) {
		super();
		this.codigo = codigo;
		this.equipo = equipo;
		this.dorsal = dorsal;
		this.nombre = nombre;
		this.tipo = tipo;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public Equipo getEquipo() {
		return equipo;
	}
	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}
	public int getDorsal() {
		return dorsal;
	}
	public void setDorsal(int dorsal) {
		this.dorsal = dorsal;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
	public ArrayList<Accion> getAcciones() {
		return acciones;
	}
	public void setAcciones(ArrayList<Accion> acciones) {
		this.acciones = acciones;
	}
	private void mostrar() {
		System.out.println("Código:" + codigo + 
				"\tEquipo:"+equipo.getNombre() + 
				"\tDorsal" + dorsal + 
				"\tNombre" + nombre + 
				"\tTipo" + tipo);
	}
}
