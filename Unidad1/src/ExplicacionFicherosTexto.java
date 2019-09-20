import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ExplicacionFicherosTexto {
	
	static Scanner t = new Scanner(System.in);
	static String nombreF = "Almacen.txt";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int opcion = 0;
		do {
			System.out.println("0-Salir");
			System.out.println("1-Leer/Mostrar");
			System.out.println("2-Insertar/Añadir material");
			System.out.println("3-Modificar material");
			System.out.println("4-Borrar material");
			
			opcion=t.nextInt();t.nextLine();
			switch(opcion) {
				case 1:
					mostrarFichero();
					break;
				case 2:
					insertarMaterial();
					break;
			}
			
		}while(opcion!=0);
	}

	private static void insertarMaterial() {
		// TODO Auto-generated method stub
		//Declaramos el fichero escritura 
		BufferedWriter fichero = null;
		try {
			//Abrimos el fichero
			fichero = new BufferedWriter(new FileWriter(nombreF, true));
			//Pedimos los datos
			String linea = "";
			System.out.println("Introduce el código");
			linea = t.nextLine();
			System.out.println("Introduce el nombre");
			linea += ";"+t.nextLine();
			System.out.println("Introduce el precio");
			linea += ";"+t.nextLine();
			System.out.println("Introduce el stock");
			linea += ";"+t.nextLine();
			//Por defecto el material está de alta
			linea += ";Sí\n";
			//Escribir línea
			fichero.write(linea);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(fichero!=null) {
				try {
					fichero.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	private static void mostrarFichero() {
		// TODO Auto-generated method stub
		
		//Declaramos el fichero para lectura
		BufferedReader fichero = null;
		
		try {
			//Abrir el fichero
			fichero = new BufferedReader(new FileReader(nombreF));
			//Recorremos el fichero hasta el final
			String linea;
			while((linea=fichero.readLine())!=null) {
				//Obtenemos cada campo de la línea
				String campos[]=linea.split(";");
				System.out.println("Código:"+campos[0]+
						"\tNombre:"+campos[1]+
						"\tPrecio:"+Float.parseFloat(campos[2])+
						"\tStock:"+Integer.parseInt(campos[3])+
						"\tBaja:"+campos[4]);				
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Fichero no encontrado");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(fichero!=null) {
				//Cerrar fichero
				try {
					fichero.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
