package ies;

import java.util.ArrayList;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.config.EmbeddedConfiguration;

public class ModeloOO {
	private ObjectContainer conexion= null;

	public ModeloOO() {
		//Abrimos la BD
		EmbeddedConfiguration config = 
				Db4oEmbedded.newConfiguration();
		config.common().objectClass(Alumno.class).cascadeOnDelete(true);
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
		boolean resultado = true;
		try {
			for(Nota n:notas) {
				//Buscar el alumno
				ObjectSet<Alumno> al= 
						conexion.queryByExample(n.getAlumno());
				if(al.hasNext()) {
					n.setAlumno(al.next());
				}
				ObjectSet<Asignatura> as = 
						conexion.queryByExample(n.getAsig());
				
				if(as.hasNext()) {
					n.setAsig(as.next());
				}
				
				conexion.store(n);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			resultado = false;
			e.printStackTrace();
		}
		
		return resultado;
	}

	public void mostrarAlumnos() {
		// TODO Auto-generated method stub
		try {
			ObjectSet<Alumno> r = conexion.queryByExample(new Alumno());
			while(r.hasNext()) {
				Alumno a = r.next();
				a.mostrar();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

	public Alumno obtenerAlumno(Alumno a) {
		// TODO Auto-generated method stub
		Alumno resultado = null;
		try {
			ObjectSet<Alumno> r = conexion.queryByExample(a);
			if(r.hasNext()) {
				resultado = r.next();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean modificarAlumno(Alumno a) {
		// TODO Auto-generated method stub
		boolean resultado=false;
		try {
			conexion.store(a.getDireccion());
			resultado = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}

	public void mostrarNotas(Alumno a) {
		// TODO Auto-generated method stub
		try {
			Nota n = new Nota();
			n.setAlumno(a);
			ObjectSet<Nota> r = conexion.queryByExample(n);
			while(r.hasNext()) {
				Nota n2 = r.next();
				n2.mostrar(true);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void mostrarAsignaturas() {
		// TODO Auto-generated method stub
		try {
			
			ObjectSet<Asignatura> r = 
					conexion.queryByExample(new Asignatura());
			while(r.hasNext()) {
				Asignatura a = r.next();
				a.mostrar();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public Asignatura obtenerAsignatura(Asignatura as) {
		// TODO Auto-generated method stub
		Asignatura resultado = null;
		try {
			
			ObjectSet<Asignatura> r = 
					conexion.queryByExample(as);
			if(r.hasNext()) {
				resultado = r.next();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean estaMatriculado(Alumno a, Asignatura as) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		try {
			Nota n = new Nota(a, as, null);
			ObjectSet<Nota> r = 
					conexion.queryByExample(n);
			if(r.hasNext()) {
				resultado = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean matricular(Alumno a, Asignatura as) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		try {
			Nota n = new Nota();
			n.setAlumno(a);
			n.setAsig(as);
			conexion.store(n);
			resultado = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}

	public Nota obtenerNotas(Alumno a, Asignatura as) {
		// TODO Auto-generated method stub
		Nota resultado = null;
		try {
			Nota n = new Nota(a, as, null);
			ObjectSet<Nota> r = 
					conexion.queryByExample(n);
			if(r.hasNext()) {
				resultado = r.next();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean anadirNota(Nota n) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		try {
			conexion.store(n.getNotas2());
			resultado = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean borrarAlumno(Alumno a) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		try {
			conexion.delete(a);
			resultado = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}
	
}
