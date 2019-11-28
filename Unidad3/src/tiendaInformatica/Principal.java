package tiendaInformatica;

import java.util.Scanner;



public class Principal {
	static Scanner t = new Scanner(System.in);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Modelo  tienda = new Modelo();
		if (tienda.getCol() != null) {
			int opcion = 0;
			do {
				System.out.println("0-Salir");
				System.out.println("1-Insertar Pieza");
				System.out.println("2-Mostrar Piezas");
				System.out.println("3-Crear Ordenador");
				System.out.println("4-Mostrar Ordenadores");
				System.out.println("5-Añadir pieza a ordenador");
				opcion = t.nextInt();
				t.nextLine();
				int id;
				Pieza p;
				Ordenador o;
				switch (opcion) {	
				case 1:
					p=new Pieza();
					System.out.println("Introduce Nombre");
					p.setNombre(t.nextLine());
					System.out.println("Introduce stock");
					p.setStock(t.nextInt());t.nextLine();
					System.out.println("Introduce Precio");
					p.setPrecio(t.nextFloat());t.nextLine();
					if(!tienda.insertarPieza(p)) {
						System.out.println("Error al insertar la pieza");
					}
					break;
				case 2:
					tienda.mostrarPiezas();
					break;
				case 3:
					o= new Ordenador();
					System.out.println("Introduce Codigo");
					o.setCodigo(t.nextLine());
					if(!tienda.existeOrdenador(o.getCodigo())) {
						if(!tienda.insertarOrdenador(o)) {
							System.out.println("Error al insertar el ordenador");
						}
					}
					else {
						System.out.println("Error: Ya existe un ordenador con ese código");
					}
					break;
				case 4:
					
					break;
				case 5:
					
					break;

				}
			} while (opcion != 0);
			tienda.cerrar();
		} else {
			System.out.println("No se ha podido conectar con la bd Ventas");
		}
	}
}
