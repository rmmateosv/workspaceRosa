package acb;

import java.util.ArrayList;

public class Equipo {
	private String nombre;
	private String localidad;
	
	ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
	
	ArrayList<Partido> partidosL = new ArrayList<Partido>();
	
	ArrayList<Partido> partidosV = new ArrayList<Partido>();
	
	public Equipo() {
		super();
	}
	public Equipo(String nombre, String localidad) {
		super();
		this.nombre = nombre;
		this.localidad = localidad;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getLocalidad() {
		return localidad;
	}
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	
	public ArrayList<Jugador> getJugadores() {
		return jugadores;
	}
	public void setJugadores(ArrayList<Jugador> jugadores) {
		this.jugadores = jugadores;
	}
	public ArrayList<Partido> getPartidosL() {
		return partidosL;
	}
	public void setPartidosL(ArrayList<Partido> partidosL) {
		this.partidosL = partidosL;
	}
	public ArrayList<Partido> getPartidosV() {
		return partidosV;
	}
	public void setPartidosV(ArrayList<Partido> partidosV) {
		this.partidosV = partidosV;
	}
	public void mostrar() {
		System.out.println("Nombre:"+nombre + "Localidad:"+localidad);
	}
}
