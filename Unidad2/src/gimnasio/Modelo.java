package gimnasio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Modelo {
	private Connection conexion = null;
	private String urlBd ="jdbc:mysql://localhost:3306/gimnasio?serverTimezone=UTC",
			usuario = "gimnasio",
			clave = "gimnasio";
	
	public Modelo() {
		
		try {
			//Cargar el driver
			Class.forName("com.mysql.jdbc.Driver");
			//Creamos la conexión
			conexion = DriverManager.getConnection(urlBd, usuario, clave);
			
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


	public String comprobarUS(String us, String cl) {
		// TODO Auto-generated method stub
		String resultado = "";
		try {
			PreparedStatement sentencia = 
					conexion.prepareStatement("select tipo "
							+ "from usuarios "
							+ "where usuario = ? and "
							+ "clave = sha2(?,224)");
			sentencia.setString(1, us);
			sentencia.setString(2, cl);
			
			ResultSet r = sentencia.executeQuery();
			
			if(r.next()) {
				
			}
			else {
				System.out.println();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultado;
	}
}
