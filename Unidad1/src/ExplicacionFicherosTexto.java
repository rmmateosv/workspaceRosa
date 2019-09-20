import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
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
			System.out.println("2-Insertar/A�adir material");
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
				case 3:
					modificarMaterial();
			}
			
		}while(opcion!=0);
	}

	private static void modificarMaterial() {
		// TODO Auto-generated method stub
		
		//Pedimos el c�digo del material a moficar
		System.out.println("Introduce el c�digo del material a modificar");
		String codigo = t.nextLine();
		
		//Declaramos el fichero original para lectura
		BufferedReader ficheroO = null;
		//Declarmos un fichero temporal para escritura
		BufferedWriter ficheroTmp = null;
		
		try {
			//Abrimos los ficheros
			ficheroO = new BufferedReader(new FileReader(nombreF));
			//El fichero temporal se sobreescribe (append=false)
			ficheroTmp = new BufferedWriter(new FileWriter("almacen.tmp",false));
			//Leemos todas las l�neas del fichero original
			String linea;
			while((linea=ficheroO.readLine())!=null) {
				//Dividimos la l�nea en campos
				String campos[] = linea.split(";");
				//Comprobamos si la l�nea le�da es la l�nea a modificar
				if(codigo.equalsIgnoreCase(campos[0])) {
					//Pedimos el nuevo nombre
					System.out.println("Introduce el nuevo nombre");
					campos[1] = t.nextLine();
					System.out.println("Introduce el nuevo precio");
					campos[2] = Float.toString(t.nextFloat());t.nextLine();
					System.out.println("Introduce el nuevo stock");
					campos[3] = Integer.toString(t.nextInt());t.nextLine();
					//Escribimos la l�nea modificada en el fichero temporal
					ficheroTmp.write(campos[0]+";"+campos[1]+";"+
					                 campos[2]+";"+campos[3]+";"+
					                 campos[4]+"\n");
				}
				else {
					//Escribimos la l�nea tal cual se ha le�do
					ficheroTmp.write(linea+"\n");
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Fichero " + nombreF + "no encontrado");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(ficheroO!=null) {
				try {
					ficheroO.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(ficheroTmp!=null) {
				try {
					ficheroTmp.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
		
		//Declarmos objetos de la clase File para borrar el 
		//fichero original y para renombrar el fichero temporal
		File fO = new File(nombreF);
		File fTmp = new File("almacen.tmp");
		
		//Borramos el fichero orinal
		if(fO.delete()) {
			//Renombramos el fichero temporal
			if(!fTmp.renameTo(fO)) {
				System.out.println("Error al renombrear " + nombreF);
			}
		}
		else {
			System.out.println("Error al borrar el fichero " + nombreF);
		}
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
			System.out.println("Introduce el c�digo");
			linea = t.nextLine();
			System.out.println("Introduce el nombre");
			linea += ";"+t.nextLine();
			System.out.println("Introduce el precio");
			linea += ";"+t.nextLine();
			System.out.println("Introduce el stock");
			linea += ";"+t.nextLine();
			//Por defecto el material est� de alta
			linea += ";S�\n";
			//Escribir l�nea
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
				//Obtenemos cada campo de la l�nea
				String campos[]=linea.split(";");
				System.out.println("C�digo:"+campos[0]+
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
