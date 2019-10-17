package ExplicacionMySQL;

import java.util.Scanner;

public class Principal {
	static Scanner t = new Scanner(System.in);
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Modelo taller = new Modelo();
		if(taller!=null) {
			int opcion = 0;
			do {
				System.out.println("0-Salir");
				System.out.println("1-Información sobre el servidor");
				System.out.println("2-Ver campos de una tabla");
				System.out.println("3-Información campos de una consulta");
				System.out.println("4-Cargar piezas de fichero de objetos");
				System.out.println("5-Mostrar piezas");
				System.out.println("6-Crear reparación");
				System.out.println("7-Añadir pieza a reparación");
				System.out.println("8-Modificar datos cliente");
				System.out.println("9-Virus borra todos los datos");
				opcion=t.nextInt();t.nextLine();
				switch(opcion) {
					case 1:
						taller.infoServidor();
						break;
					case 2:
						System.out.println("Introduce tabla");
						String nombre = t.nextLine();
						taller.verCamposTabla(nombre);
						break;
					case 3:
						System.out.println("Introduce una consulta SQL");
						String consulta = t.nextLine();
						taller.verCamposConsulta(consulta);
						break;
					
				}
			}while(opcion!=0);
		}
		else {
			System.out.println("No se ha podido conectar con la bd Taller");;
		}
	}

}
