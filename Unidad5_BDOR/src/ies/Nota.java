package ies;

import java.util.ArrayList;

public class Nota {
	private Alumno alumno;
	private Asignatura asig;
	private ArrayList<String[]> notas = new ArrayList<String[]>();
	public Nota() {
		
	}
	public Nota(Alumno alumno, Asignatura asig, ArrayList<String[]> notas) {
	
		this.alumno = alumno;
		this.asig = asig;
		this.notas = notas;
	}
	public Alumno getAlumno() {
		return alumno;
	}
	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}
	public Asignatura getAsig() {
		return asig;
	}
	public void setAsig(Asignatura asig) {
		this.asig = asig;
	}
	public ArrayList<String[]> getNotas() {
		return notas;
	}
	public void setNotas(ArrayList<String[]> notas) {
		this.notas = notas;
	}
	
	public void mostrar() {
		System.out.println("Alumno:" + alumno.getCodigo() + 
				"\tAsig:"+asig.getNombreC());
		for(String[] n:notas) {
			System.out.println("Fecha:"+n[0] + "\tNota:"+n[1]);
		}
	}

}
