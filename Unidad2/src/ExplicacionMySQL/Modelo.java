package ExplicacionMySQL;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Modelo {
	private Connection conexion = null;
	private String url = "jdbc:mysql://localhost:3306/taller";
	private String usuario = "root";
	private String clave = "root";

	public Modelo() {
		try {
			//Cargamos el driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			//Creamos la conexión a la BD
			conexion = DriverManager.getConnection(url, usuario, clave);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error al cargar el driver");
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

	public void infoServidor() {
		// TODO Auto-generated method stub
		
		try {
			//Obtener metadatos
			DatabaseMetaData metadatos= conexion.getMetaData();
			System.out.println("Sistema gestor de BD " + metadatos.getDatabaseProductName());
			System.out.println("Versión " + metadatos.getDatabaseProductVersion());
			System.out.println("Bases de datos ");
			ResultSet bds = metadatos.getCatalogs();
			while(bds.next()) {
				System.out.println(bds.getString(1));
			}
			System.out.println("Tablase de la BD");
			ResultSet tablas = metadatos.getTables("taller", null, null, null);
			while(tablas.next()) {
				System.out.println(tablas.getString(3));
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
