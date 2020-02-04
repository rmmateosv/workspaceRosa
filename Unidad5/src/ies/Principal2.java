package ies;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;


public class Principal2 {
	public static Scanner t = new Scanner(System.in);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ModeloOO iesOO = new ModeloOO();
		ModeloOR iesOR = new ModeloOR();
		if(iesOO.getConexion()!=null) {
			int opcion = 0;
			do {
				System.out.println("0-Salir");
				System.out.println("1- Importar datos de Postgre SQL");
				
				
				opcion = t.nextInt();t.nextLine();
				
				
				switch(opcion) {
				case 1:
					//Importamos asignaturas
					ArrayList<Asignatura> asigs = iesOR.obtenerAsis();
					if(!iesOO.crearAsig(asigs)) {
						System.out.println("Error al importar asignaturas");
					}
					else {
						ArrayList<Alumno> alum = iesOR.obtenerAlum();
						if(!iesOO.importarAlumnos(alum)) {
							System.out.println("Error al importar alumos");
						}
						else {
							ArrayList<Nota> notas = iesOR.obtenerNotas();
							if(!iesOO.importarNotas(notas)) {
								System.out.println("Error al importar notas");
							}
						}
					}
					break;
				
				}
			}while (opcion!=0);
			iesOO.cerrar();
		}
		
	}

}
