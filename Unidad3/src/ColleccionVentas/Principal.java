package ColleccionVentas;

import java.util.Date;
import java.util.Scanner;

public class Principal {
	static Scanner t = new Scanner(System.in);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Modelo ventas = new Modelo();
		if (ventas.getCol() != null) {
			int opcion = 0;
			do {
				System.out.println("0-Salir");
				System.out.println("1-Mostrar clientes");

				opcion = t.nextInt();
				t.nextLine();
				
				switch (opcion) {
				
				case 1:
					ventas.mostrarClientes();
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
			ventas.cerrar();
		} else {
			System.out.println("No se ha podido conectar con la bd Ventas");
		}

	}

}
