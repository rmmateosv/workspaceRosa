package ies;

import java.util.ArrayList;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;

public class ModeloOO {
	private ObjectContainer conexion= null;

	public ModeloOO() {
		//Abrimos la BD
		conexion = Db4oEmbedded.openFile("ies.db4o");
	}

	public ObjectContainer getConexion() {
		return conexion;
	}

	public void setConexion(ObjectContainer conexion) {
		this.conexion = conexion;
	}
	
	public void cerrar() {
		conexion.close();
	}

	public boolean crearAsig(ArrayList<Asignatura> asigs) {
		// TODO Auto-generated method stub
		boolean resultado = true;
		try {
			for(Asignatura a:asigs) {
				conexion.store(a);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			resultado = false;
			e.printStackTrace();
		}
		
		return resultado;
	}

	public boolean importarAlumnos(ArrayList<Alumno> alum) {
		// TODO Auto-generated method stub
		boolean resultado = true;
		try {
			for(Alumno a:alum) {
				conexion.store(a);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			resultado = false;
			e.printStackTrace();
		}
		
		return resultado;
	}

	public boolean importarNotas(ArrayList<Nota> notas) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
