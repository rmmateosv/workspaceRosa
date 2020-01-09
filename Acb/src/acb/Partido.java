package acb;

import java.util.ArrayList;

public class Partido {
	private int codigo;
	private Equipo local;
	private Equipo visitante;
	
	private ArrayList<Accion> acciones = new ArrayList<Accion>();
	
	public Partido() {
		super();
	}
	public Partido(int codigo, Equipo local, Equipo visitante) {
		super();
		this.codigo = codigo;
		this.local = local;
		this.visitante = visitante;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public Equipo getLocal() {
		return local;
	}
	public void setLocal(Equipo local) {
		this.local = local;
	}
	public Equipo getVisitante() {
		return visitante;
	}
	public void setVisitante(Equipo visitante) {
		this.visitante = visitante;
	}
	
	public ArrayList<Accion> getAcciones() {
		return acciones;
	}
	public void setAcciones(ArrayList<Accion> acciones) {
		this.acciones = acciones;
	}
	public void mostrar() {
		System.out.println("Codigo:"+codigo + 
				"\tLocal:"+local.getNombre()+
				"\tVisitante:"+visitante.getNombre());
		
	}
	
}
