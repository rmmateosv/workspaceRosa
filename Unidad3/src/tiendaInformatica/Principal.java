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
				switch (opcion) {	
				case 1:
					
					break;
				case 2:
					
					break;
				case 3:
					
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
