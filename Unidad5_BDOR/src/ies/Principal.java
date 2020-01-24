package ies;

import java.util.Date;
import java.util.Scanner;


public class Principal {
	public static Scanner t = new Scanner(System.in);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Modelo ies = new Modelo();
		if(ies.getConexion()!=null) {
			int opcion = 0;
			do {
				System.out.println("0-Salir");
				System.out.println("1-Mostrar Alumnos");
				
				opcion = t.nextInt();t.nextLine();
				
				switch(opcion) {
				case 1:
					ies.mostrarAlumnos();
					break;
				
				}
			}while (opcion!=0);
			ies.cerrar();
		}
		
	}

}
