package biblioteca;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;



public class Principal {
	public static Scanner t = new Scanner(System.in);
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Modelo biblioteca = new Modelo();
		if(biblioteca.getEm()!=null) {
			int opcion = 0;
			do {
				System.out.println("0-Salir");
				System.out.println("1-Crear Socio");
				System.out.println("2-Crear Libro");
				System.out.println("3-Modificar n� de ejemplares");
				System.out.println("4-Crear Pr�stamos");
				System.out.println("5-Devolver Pr�stamo");
				System.out.println("6-Mostrar los datos de un socio, incluido sus pr�stamos");
				System.out.println("7-Mostrar el n� de pr�stamos pendientes de cada socio");
				System.out.println("8-Mostrar el n� de pr�stamos pendientes de devoluci�n");
				System.out.println("9-Mostrar el n� de libros y el n� de ejemplares totales de la biblioteca");
				System.out.println("10-Borrar un libro con o sin pr�stamos");
				System.out.println("11-Borrar un socio con o sin pr�stamos");
				opcion = t.nextInt();t.nextLine();
				
				Socio s;
				Libro l;
				switch(opcion) {
				case 1:
					s = new Socio();
					System.out.println("Introduce Nif");
					s.setNif(t.nextLine());
					if((biblioteca.obtenerSocio(s))==null) {
						System.out.println("Nombre");
						s.setNombre(t.nextLine());
						s.setSancionado(false);
						if(!biblioteca.crearSocio(s)) {
							System.out.println("Error al crear el socio");
						}
					}
					else {
						System.out.println("Error: Ya existe un socio con ese NIF");
					}
					break;
				case 2:
					l = new Libro();
					System.out.println("Isbn:");
					l.setIsbn(t.nextLine());
					if((biblioteca.obtenerLibro(l))==null) {
						System.out.println("Ejemplares");
						l.setNumEjemplares(t.nextInt());t.nextLine();
						System.out.println("T�tulo");
						l.setTitulo(t.nextLine());
						if(!biblioteca.altaLibro(l)) {
							System.out.println("Error al crear libro");
						}
					}
					else {
						System.out.println("Error: Ya existe");
					}
					break;
				case 3:
					
					break;
				case 4:
					
					break;
				case 5:
					
					break;
				
				}
			}while (opcion!=0);
		}
		biblioteca.cerrar();
	}

}
