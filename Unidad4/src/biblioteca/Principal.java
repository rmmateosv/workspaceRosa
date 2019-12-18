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
				System.out.println("10-Borrar un libro con o sin pr�stamos/Borrado en cascada");
				System.out.println("11-Borrar un socio con o sin pr�stamos/Borrado restringido");
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
					biblioteca.mostrarLibros();
					System.out.println("Introudce ISBN");
					l = new Libro();
					l.setIsbn(t.nextLine());
					l=biblioteca.obtenerLibro(l);
					if(l!=null) {
						System.out.println("Introduce nuevo n� de ejemplares");
						l.setNumEjemplares(t.nextInt());t.nextLine();
						if(!biblioteca.modificarLibro(l)) {
							System.out.println("Error al modificar el n� de ejemplares");
						}
					}
					else {
						System.out.println("Error, no existe el libro");
					}
					break;
				case 4:
					biblioteca.mostrarSocios();
					s = new Socio();
					System.out.println("Introduce NIF");
					s.setNif(t.nextLine());
					s=biblioteca.obtenerSocio(s);
					if(s!=null) {
						s.mostrar(true);
						if(!s.isSancionado()) {
							//Calculamos el n� de pr�stamos sin devolver
							//Se puede hacer de dos forma, recorriendo la 
							//lista de pr�stamos del socio o
							//ejecutando una consulta a la BD
							//Lo hacemos de las dos formas y dejarems 
							//una de las dos comentada
							long numPrestamosPtes=0;
							/*for(Prestamo p:s.getPrestamos()) {
								if(p.getFechaDevolReal()==null) {
									numPrestamosPtes++;
								}
							}*/
							numPrestamosPtes = biblioteca.obtenerPrestPtes(s);
							
							if(numPrestamosPtes<2) {
								biblioteca.mostrarLibros();
								System.out.println("Introduce ISBN");
								l= new Libro();
								l.setIsbn(t.nextLine());
								l=biblioteca.obtenerLibro(l);
								if(l!=null) {
									if(l.getNumEjemplares()>=1) {
										if(!biblioteca.crearPrestamo(s,l)){
											System.out.println("Error al crear el pr�stamo");
										}
									}
									else {
										System.out.println("Error, no hay ejemplares suficientes");
									}
								}
								else {
									System.out.println("Error, no existe el libro");
								}
								
							}
							else {
								System.out.println("Error, tiene 2 o m�s pr�stamos sin devolver");
							}
						}
						else {
							System.out.println("Error, socio sancionado");
						}
					}
					else {
						System.out.println("Error, socio no existe");
					}
					break;
				case 5:
					biblioteca.mostrarSocios();
					System.out.println("Introduce NIF");
					s = new Socio();
					s.setNif(t.nextLine());
					s=biblioteca.obtenerSocio(s);
					if(s!=null) {
						s.mostrar(true);
						System.out.println("Introduce ISBN");
						l = new Libro();
						l.setIsbn(t.nextLine());
						l = biblioteca.obtenerLibro(l);
						if(l!=null) {
							//Rellenamos la fecha real de devolucion
							//del pr�stamo
							for(Prestamo p :s.getPrestamos()) {
								if(p.getId().getLibro()==l && 
										p.getFechaDevolReal()==null) {
									
									if(!biblioteca.devolverPrestamo(p)) {
										System.out.println("Error al devolver el pr�stamo");
									}
								}
							}
						}
						else {
							System.out.println("Error, libro no existe");
						}
					}
					else {
						System.out.println("Error, socio no existe");
					}
					break;
				case 7:
					biblioteca.mostrarPrestamosPtes();
					break;
				case 8:
					biblioteca.numPrestamosPtes();
					break;
				case 10:
					biblioteca.mostrarLibros();
					l = new Libro();
					System.out.println("Isbn:");
					l.setIsbn(t.nextLine());
					l=biblioteca.obtenerLibro(l);
					if(l!=null) {
						if(l.getPrestamos().size()>0) {
							System.out.println("Libro con pr�stamos -> se borran tb los pr�stamos");
						}
						if(!biblioteca.borrarLibro(l)) {
							System.out.println("Error al borrar el libro");
						}
					}
					else {
						System.out.println("Error, libro no existe");
					}
					break;
				case 11:
					biblioteca.mostrarSocios();
					s = new Socio();
					System.out.println("NIF:");
					s.setNif(t.nextLine());
					s=biblioteca.obtenerSocio(s);
					if(s!=null) {
						if(s.getPrestamos().size()>0) {
							System.out.println("No se va a borrar, va a dar un error");
						}
						if(!biblioteca.borrarSocio(s)) {
							System.out.println("Error al borrar el socio");
						}
					}
					else {
						System.out.println("Error, socio no existe");
					}
					break;
				
				}
			}while (opcion!=0);
		}
		biblioteca.cerrar();
	}

}
