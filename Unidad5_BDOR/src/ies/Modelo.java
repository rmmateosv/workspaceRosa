package ies;

import java.sql.Connection;
import java.sql.DriverManager;
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
							+ "from alumno");
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
			
}
