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
			if(gimnasio.comprobarUS(us,cl).equals('A')) {
				
			}
			else {
				
			}
			//Cerramos la BD
			gimnasio.cerrar();
		}
		else {
			System.out.println("Error:No se ha podido conectar con la BD");
		}
	}

}
