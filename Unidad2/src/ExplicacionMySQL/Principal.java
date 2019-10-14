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
				System.out.println("2-Ver tablas de la BD");
				System.out.println("3-Ver campos de una tabla");
				System.out.println("4-Información campos de una consulta");
				opcion=t.nextInt();t.nextLine();
				switch(opcion) {
					case 1:
						taller.infoServidor();
						break;
				}
			}while(opcion!=0);
		}
		else {
			System.out.println("No se ha podido conectar con la bd Taller");;
		}
	}

}
