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
				System.out.println("2-Modificar dirección");
				System.out.println("3-Mostrar notas de un alumno");
				System.out.println("4-Poner nota a alumno");
				
				opcion = t.nextInt();t.nextLine();
				
				Alumno a;
				switch(opcion) {
				case 1:
					ies.mostrarAlumnos();
					break;
				case 2:
					ies.mostrarAlumnos();
					System.out.println("Introduce el código");
					a = new Alumno();
					a.setCodigo(t.nextInt());t.nextLine();
					if(ies.existeAlumo(a)) {
						System.out.println("Nueva dirección");
						a.setDireccion(new Direccion());
						System.out.println("Tipo via:");
						a.getDireccion().setTipoV(t.nextLine());
						System.out.println("Nombre via:");
						a.getDireccion().setNombreV(t.nextLine());
						System.out.println("Nº:");
						a.getDireccion().setNumero(t.nextInt());
						t.nextLine();
						System.out.println("CP:");
						a.getDireccion().setCp(t.nextInt());
						t.nextLine();
						if(!ies.modificarDireccion(a)) {
							System.out.println("Error al modificar la dirección");
						}
					}
					else {
						System.out.println("No existe el alumno");
					}
					break;
				case 3:
					ies.mostrarAlumnos();
					System.out.println("Introduce el código");
					a = new Alumno();
					a.setCodigo(t.nextInt());t.nextLine();
					if(ies.existeAlumo(a)) {
						ies.mostrarNotas(a);
					}
					else {
						System.out.println("No existe el alumno");
					}
					break;
				
				}
			}while (opcion!=0);
			ies.cerrar();
		}
		
	}

}
