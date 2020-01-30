package ies;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class Modelo {
	Connection conexion = null;
	private String url = "jdbc:postgresql://localhost:5432/IES";
	private String usuario = "postgres", clave="root";

	public Modelo() {
		try {
			Class.forName("org.postgresql.Driver");
			
			conexion = DriverManager.getConnection(url, usuario, clave);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Connection getConexion() {
		return conexion;
	}

	public void setConexion(Connection conexion) {
		this.conexion = conexion;
	}
	
	public void cerrar() {
		try {
			conexion.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void mostrarAlumnos() {
		// TODO Auto-generated method stub
		try {
			Statement consulta = conexion.createStatement();
			ResultSet  r = 
					consulta.executeQuery("select  codigo,"
							+ "nombre, (dir).tipoV, "
							+ "(dir).nombreV,"
							+ "fechaM "
							+ "from alumno "
							+ "order by codigo");
			while(r.next()) {
				Alumno a = new Alumno(r.getInt(1), 
						r.getString(2), 
						new Direccion(r.getString(3), r.getString(4), 0, 0), 
						new Date(r.getDate(5).getTime()));
				a.mostrar();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean existeAlumo(Alumno a) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		try {
			PreparedStatement sentencia = 
					conexion.prepareStatement("select * "
							+ "from alumno "
							+ "where codigo = ?");
			sentencia.setInt(1, a.getCodigo());
			ResultSet r = sentencia.executeQuery();
			if(r.next()) {
				resultado = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean modificarDireccion(Alumno a) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		try {
			PreparedStatement sentencia = 
					conexion.prepareStatement("update alumno "
							+ "set dir = (?,?,?,?) "
							+ "where codigo = ?");
			sentencia.setString(1, a.getDireccion().getTipoV());
			sentencia.setString(2, a.getDireccion().getNombreV());
			sentencia.setInt(3, a.getDireccion().getNumero());
			sentencia.setInt(4, a.getDireccion().getCp());
			sentencia.setInt(5, a.getCodigo());
			int r = sentencia.executeUpdate();
			if(r==1) {
				resultado  = true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

	public void mostrarNotas(Alumno a) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement sentencia= 
					conexion.prepareStatement("select * "
							+ "from nota "
							+ "where alumno = ?");
			sentencia.setInt(1, a.getCodigo());
			ResultSet r = sentencia.executeQuery();
			while(r.next()) {
				Nota n = new Nota();
				n.setAlumno(new Alumno(r.getInt(1), null, null, null));
				n.setAsig(new Asignatura(r.getString(2), null));
				//Recuperamos el array de notas
				Array notas = r.getArray(3);
				String[][] tmp = (String[][]) notas.getArray();
				//Rellenamos el arraylist de notas 
				for(String[] no: tmp) {
					n.getNotas().add(no);
				}
				n.mostrar();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
			
}
