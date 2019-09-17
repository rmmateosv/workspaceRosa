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
			System.out.println("2-Pedir ruta y mostrar informaci�n");
			System.out.println("3-Mostrar contenido de carpeta");
			opcion=t.nextInt();t.nextLine();
			switch(opcion) {
			case 1:
				mostrarRutaActual();
				break;
			case 2:
				infoRuta();
				break;
			case 3:
				mostrarContenido();
				break;
			}
			
		}while(opcion!=0);
	}

	private static void mostrarContenido() {
		// TODO Auto-generated method stub
		//Pedir carpeta
		System.out.println("Introduce la ruta de una carpeta");
		String ruta = t.nextLine();
		//Creamos el objeto File de la ruta
		File fRuta = new File(ruta);
		//Comprobamos que exsite y que es una carperta
		if(fRuta.exists() && fRuta.isDirectory()) {
			//Obtener el contenido de la carpeta
			File[] contenido = fRuta.listFiles();
			//Recorremos el contenido para mostrar la informaci�n
			//de cada elemento
			for(File elemento:contenido) {
				//Nombre
				System.out.print(elemento.getName()+"\t");
				//tipo
				if(elemento.isDirectory())
					System.out.print("D"+"\t");
				else
					System.out.print("F"+"\t");
				//Permiso de lectura
				if(elemento.canRead())
					System.out.print("r");
				else
					System.out.print("-");
				//Permiso de escritura
				if(elemento.canWrite())
					System.out.print("w");
				else
					System.out.print("-");
				//Permiso de ejecuci�n
				if(elemento.canExecute())
					System.out.println("x");
				else
					System.out.println("-");
			}
		}
		else {
			System.out.println("La ruta no existe o no es una carpeta");
		}
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
			
			//Mostrar fecha de modificaci�n
			Date fecha = new Date(fRuta.lastModified());
			
			System.out.println("Fecha modificaci�n:"+ df.format(fecha));
			
			//Mostrar tama�o
			System.out.println("Tama�o:" + fRuta.length());
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
