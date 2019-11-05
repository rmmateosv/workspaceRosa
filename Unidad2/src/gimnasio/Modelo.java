package gimnasio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Modelo {
	private Connection conexion = null;
	private String urlBd ="jdbc:mysql://localhost:3306/gimnasio?serverTimezone=UTC",
			usuario = "gimnasio",
			clave = "gimnasio";
	
	public Modelo() {
		
		try {
			//Cargar el driver
			Class.forName("com.mysql.cj.jdbc.Driver");
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

	//Devuelve el tipo de usuario si el usario exsite
	//Si no existe devuelve NE
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
				resultado = r.getString(1);
			}
			else {
				System.out.println("Error: Usuario no existe");
				resultado = "NE";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultado;
	}


	public void mostrarClientes() {
		// TODO Auto-generated method stub
		try {
			Statement sentencia = conexion.createStatement();
			ResultSet r = sentencia.executeQuery("select * from cliente");
			while(r.next()) {
				Cliente c = new 
				Cliente(r.getInt(1), 
						new Usuario(r.getString(2), null), 
						r.getString(3), r.getString(4), 
						r.getString(5), r.getString(6), r.getBoolean(7));
				c.mostrar();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}


	public boolean existeUS(String usuario) {
		// TODO Auto-generated method stub
		boolean resultado=false;
		
		try {
			PreparedStatement sentencia = conexion.prepareStatement
					("select * from usuarios where usuario=?");
			sentencia.setString(1, usuario);
			ResultSet r = sentencia.executeQuery();
			if(r.next()) {
				resultado=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultado;
	}
}
