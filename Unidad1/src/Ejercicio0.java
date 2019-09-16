import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

//Clase File - Paquete java.io
public class Ejercicio0 {

	static Scanner t = new Scanner(System.in);
	
	//Declaramos el formato de fecha con la que vamos a trabajar
	static SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int opcion = 0;
		do {
			System.out.println("0-Salir");
			System.out.println("1-Mostrar la ruta absoluta "
					+ "de la carpeta actual");
			System.out.println("2-Pedir ruta y mostrar información");
			opcion=t.nextInt();t.nextLine();
			switch(opcion) {
			case 1:
				mostrarRutaActual();
				break;
			case 2:
				infoRuta();
				break;
			}
			
		}while(opcion!=0);
	}

	private static void infoRuta() {
		// TODO Auto-generated method stub
		System.out.println("Introduce una ruta");
		String ruta = t.nextLine();
		
		//Creamos objeto File a la ruta introducida
		File fRuta = new File(ruta);
		
		//Comprobamos si existe
		if(fRuta.exists()) {
			//Mostramos si es fichero o carpeta
			if(fRuta.isDirectory())
				System.out.println("La ruta es una carpeta");
			else
				System.out.println("La ruta es un fichero");
			
			//Mostrar fecha de modificación
			Date fecha = new Date(fRuta.lastModified());
			
			System.out.println("Fecha modificación:"+ df.format(fecha));
			
			//Mostrar tamaño
			System.out.println("Tamaño:" + fRuta.length());
		}
		else {
			System.out.println("La ruta introducida no existe");
		}
	}

	private static void mostrarRutaActual() {
		// TODO Auto-generated method stub
		
		//Declaramos un objeto File que apunte a la carpeta actual
		File carpeta = new File(".");
		//Mostramos la ruta absoluta de mi carpeta actual
		System.out.println(carpeta.getAbsolutePath());
	}

}
