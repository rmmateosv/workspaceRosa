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

	//Devuelve -1 si se produce error
	//si no, devuelve el id del cliente creado
	public int insertarCliente(Cliente c) {
		// TODO Auto-generated method stub
		int resultado = -1;
		
		try {
			//Hay que actualizar dos tablas, por lo tanto usamos transacciones
			conexion.setAutoCommit(false);
			//Insert en tabla usuarios
			PreparedStatement sentencia = 
					conexion.prepareStatement("insert into usuarios values "
							+ "(?,sha2(?,224),?)");
			sentencia.setString(1, c.getUsuario().getUsuario());
			sentencia.setString(2, c.getUsuario().getUsuario());
			sentencia.setString(3, c.getUsuario().getTipo());
			
			int r = sentencia.executeUpdate();
			if(r==1) {
				sentencia = conexion.prepareStatement(
						"insert into cliente values "
						+ "(null,?,?,?,?,?,?)",
						Statement.RETURN_GENERATED_KEYS);
				sentencia.setString(1, c.getUsuario().getUsuario());
				sentencia.setString(2, c.getDni());
				sentencia.setString(3, c.getApellidos());
				sentencia.setString(4, c.getNombre());
				sentencia.setString(5, c.getTelefono());
				sentencia.setBoolean(6, true);
				r = sentencia.executeUpdate();
				if(r==1) {
					conexion.commit();
					//Devolvemos el id del cliente creado
					ResultSet ids = sentencia.getGeneratedKeys();
					if(ids.next()) {
						resultado = ids.getInt(1);
					}
				}
				else {
					conexion.rollback();
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultado;
	}
}
