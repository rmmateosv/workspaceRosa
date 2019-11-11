package gimnasio;

import java.util.Scanner;

public class Principal {
	public static Scanner t = new Scanner(System.in);
	private static Modelo gimnasio;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		gimnasio = new Modelo();
		if(gimnasio.getConexion()!=null) {
			//Pedimos usuario y clave
			String us, cl;
			System.out.println("Usuario:");
			us = t.nextLine();
			System.out.println("Clave");
			cl = t.nextLine();
			
			switch(gimnasio.comprobarUS(us,cl)) {
			case "A":
				menuAdmin();
				break;
			case "C":
				menuCliente(us);
				break;
			}
			
			//Cerramos la BD
			gimnasio.cerrar();
		}
		else {
			System.out.println("Error:No se ha podido conectar con la BD");
		}
	}

	private static void menuCliente(String usuario) {
		// TODO Auto-generated method stub
		int opcion = 0;
		do {
			System.out.println("0-Salir");
			System.out.println("1-Ver mis actividades");
			System.out.println("2-Inscribirse en una actividad");
			System.out.println("3-Borrarse de una actividad");
			System.out.println("4-Ver recibos");
			opcion = t.nextInt();t.nextLine();
			Actividad a;
			switch(opcion) {
			case 1:
				gimnasio.mostrarActividadesCliente(usuario);
				break;
			case 2:
				gimnasio.mostrarActivides();
				System.out.println("Introduce el c�digo de actividad");
				a = new Actividad();
				a.setId(t.nextInt());t.nextLine();
				a = gimnasio.obtenerActividad(a.getId());
				if(a==null) {
					System.out.println("Error la actividad no existe");
				}
				else {
					//Comprobamos si ya est� inscrito
					if(!gimnasio.estaInscirto(usuario,a)) {
						if(!gimnasio.inscribirEnActividad(usuario,a)) {
							System.out.println("Error al inscribirse en la actividad");
						}
					}
					else {
						System.out.println("Error: Ya est� inscrito en la actividad");
					}
				}
				break;
			case 3:
				gimnasio.mostrarActividadesCliente(usuario);
				System.out.println("Introduce c�digo actividad");
				a = new Actividad();
				a.setId(t.nextInt());t.nextLine();
				if(!gimnasio.borrarActividadCliente(usuario, a)) {
					System.out.println("Error al darse de baja de la actividad");
				}
				break;
			case 4:
				gimnasio.mostrarRecibosCliente(usuario);
				break;
			}
		}while (opcion!=0);
	}

	private static void menuAdmin() {
		// TODO Auto-generated method stub
		int opcion = 0;
		do {
			System.out.println("0-Salir");
			System.out.println("1-Gestionar clientes");
			System.out.println("2-Gestionar actividades");
			System.out.println("3-Generar recibos");
			System.out.println("4-Pagar Recibos");
			System.out.println("5-Mostrar recibos");
			opcion = t.nextInt();t.nextLine();
			switch(opcion) {
			case 1:
				gestionarClientes();
				break;
			
			}
		}while (opcion!=0);
	}

	private static void gestionarClientes() {
		// TODO Auto-generated method stub
		int opcion = 0;
		do {
			System.out.println("0-Salir");
			System.out.println("1-Mostrar Clientes");
			System.out.println("2-Insertar Cliente");
			System.out.println("3-Modificar Cliente");
			System.out.println("4-Borrar Cliente");
			opcion = t.nextInt();t.nextLine();
			Cliente c;
			switch(opcion) {
			case 1:
				gimnasio.mostrarClientes();
				break;
			case 2:
				c = new Cliente();
				System.out.println("Nombre de usuario");
				c.setUsuario(new Usuario());
				c.getUsuario().setUsuario(t.nextLine());
				//Comprobamos que no existe el usuario
				if(!gimnasio.existeUS(c.getUsuario().getUsuario())) {
					System.out.println("Introduce DNI:");
					c.setDni(t.nextLine());
					System.out.println("Introduce Nombre:");
					c.setNombre(t.nextLine());
					System.out.println("Introduce Apellidos:");
					c.setApellidos(t.nextLine());
					System.out.println("Introduce Tel�fono:");
					c.setTelefono(t.nextLine());
					c.setBaja(true);
					c.getUsuario().setTipo("C");
					int id = gimnasio.insertarCliente(c);
					if(id==-1) {
						System.out.println("Error al insertar el cliente");
					}
					else {
						System.out.println("Cliente n�mero "+id+" insertado");
					}
						
					
				}
				else {
					System.out.println("Error: el usuario ya existe");
				}
				break;
			case 3:
				//Solamente modificamos el nombre, apellidos y tel�fono
				gimnasio.mostrarClientes();
				System.out.println("Introduce el id del cliente a modificar");
				c = new Cliente();
				c.setId(t.nextInt());t.nextLine();
				c= gimnasio.obtenerCliente(c.getId());
				if(c==null) {
					System.out.println("Error: Cliente no existe");
				}
				else {
					System.out.println("Nuevo Nombre");
					c.setNombre(t.nextLine());
					System.out.println("Nuevo Apellido");
					c.setApellidos(t.nextLine());
					System.out.println("Nuevo Tel�fono");
					c.setTelefono(t.nextLine());
					if(!gimnasio.modificarCliente(c)) {
						System.out.println("Error al moficiar el cliente");
					}
				}
				
				break;
			case 4:
				gimnasio.mostrarClientes();
				System.out.println("Introduce id de cliente");
				c = new Cliente();
				c.setId(t.nextInt()); t.nextLine();
				c = gimnasio.obtenerCliente(c.getId());
				if(c==null) {
					System.out.println("Error: No existe el cliente");
				}
				else {
					if(c.isBaja()) {
						System.out.println("El cliente ya est� de baja");
					}
					else {
						if(!gimnasio.bajaCliente(c)){
							System.out.println("No se ha podido dar de baja el cliente");
						}
					}
				}
				break;
			}
		}while (opcion!=0);
	}

}
