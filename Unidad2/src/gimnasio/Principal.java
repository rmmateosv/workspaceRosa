package gimnasio;

import java.util.Scanner;

public class Principal {
	public static Scanner t = new Scanner(System.in);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Modelo gimnasio = new Modelo();
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
				menuCliente();
				break;
			}
			
			//Cerramos la BD
			gimnasio.cerrar();
		}
		else {
			System.out.println("Error:No se ha podido conectar con la BD");
		}
	}

	private static void menuCliente() {
		// TODO Auto-generated method stub
		
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
			switch(opcion) {
			case 1:
				mostrarClientes();
				break;
			
			}
		}while (opcion!=0);
	}

}
