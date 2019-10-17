package ExplicacionMySQL;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

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
				//Mostramos el dato que hay en la 1ª columna del resultset
				//Hay que ver la ayuda ya que cada ResultSet tiene una estructura
				//En este caso hay una única columna con el nombre de cada BD
				System.out.println(bds.getString(1));
			}
			System.out.println("Tablase de la BD");
			//El método necesita 4 parámetros -> ver la ayuda
			//1.- La BD de la que queremos recuperar las tablas o null para todas las bd
			//2.- Patrón con el nombre del esquema o null para todos los esquemas
			//3.- Patrón con el nombre de las tablas que queremos recuperar o null para todas las tablas
			//4.- Tipo de tabla o null para todos los tipos de tablas
			ResultSet tablas = metadatos.getTables("taller", null, null, null);
			while(tablas.next()) {
				//Mostramos la columna 3 del result set que es donde está
				//el nombre de la tabla -> Ver ayuda del método
				System.out.println(tablas.getString(3));
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void verCamposTabla(String tabla) {
		// TODO Auto-generated method stub
		//Obtenemos los metadatos
		try {
			DatabaseMetaData metadatos = conexion.getMetaData();
			//Obtenemos los campos de la tabla
			ResultSet campos = metadatos.getColumns("taller", null, tabla, null);
			while(campos.next()) {
				System.out.println("Campo:"+ campos.getString(4)+
						"Tipo SQL:"+campos.getInt(5)+
						"Tipo:"+campos.getString(6) + 
						"Tamaño:"+campos.getInt(7));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void verCamposConsulta(String consulta) {
		// TODO Auto-generated method stub
		try {
			//Declaramos la consulta a ejecutar
			Statement c = conexion.createStatement();
			//Ejecutamos la consulta
			ResultSet resultado = c.executeQuery(consulta);
			//Obtenemos metadatos
			ResultSetMetaData metadatos= resultado.getMetaData();
			System.out.println("Columnas:"+metadatos.getColumnCount());
			//DAtos de las columnas
			for(int i=1;i<=metadatos.getColumnCount();i++) {
				System.out.println("Campo:"+ metadatos.getColumnName(i)+
						"\tTipo:"+metadatos.getColumnTypeName(i)+
						"\tTabla:"+metadatos.getTableName(i));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
