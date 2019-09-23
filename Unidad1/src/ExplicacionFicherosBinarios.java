import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ExplicacionFicherosBinarios {
	
	static Scanner t = new Scanner(System.in);
	static String nombreFT = "Almacen.txt";
	static String nombreFB = "Almacen.bin";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int opcion = 0;
		do {
			System.out.println("0-Salir");
			System.out.println("1-Crear fichero binario a partir de "
					+ "fichero de texto");
			System.out.println("2-Leer/Mostrar");
			System.out.println("3-Insertar/Añadir material");
			System.out.println("4-Modificar material");
			System.out.println("5-Borrar material");
			
			opcion=t.nextInt();t.nextLine();
			switch(opcion) {
				case 1:
					crearFicheroBinario();
					break;
				case 2:
					
					break;
				case 3:
					
					break;
				case 4:
					
					break;
			}
			
		}while(opcion!=0);
	}

	private static void crearFicheroBinario() {
		// TODO Auto-generated method stub
		//Declaramos los objetos para trabajar con los ficheros
		BufferedReader ficheroT = null;
		DataOutputStream ficheroB = null;
		
		try {
			//Abrimos ficheros
			ficheroT = new BufferedReader(new FileReader(nombreFT));
			//Siempre creamos el fichero binario (append=false)
			ficheroB = new DataOutputStream(
					new FileOutputStream(nombreFB,false));
			//Recorremos todas las líneas del fichero de texto
			String linea;
			while((linea=ficheroT.readLine())!=null) {
				//Obtenemos los campos de la línea de texto leída
				String campos[] = linea.split(";");
				//Escribimos los campos en el fichero binario
				//Código
				ficheroB.writeChars(campos[0]+"\n");
				//Nombre
				ficheroB.writeChars(campos[1]+"\n");
				//Precio
				ficheroB.writeFloat(Float.parseFloat(campos[2]));
				//Stock
				ficheroB.writeInt(Integer.parseInt(campos[3]));
				//Alta
				if(campos[4].equalsIgnoreCase("Sí"))
					ficheroB.writeBoolean(true);
				else
					ficheroB.writeBoolean(false);
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Fichero no encontrado "+nombreFT);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(ficheroT!=null) {
				try {
					ficheroT.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(ficheroB!=null) {
				try {
					ficheroB.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	
}
