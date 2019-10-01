import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.RandomAccessFile;
import java.util.Scanner;

import FicherosDeObjetos.Pieza;

public class ExplicacionFicherosAccesoAleatorio {
	static Scanner t = new Scanner(System.in);
	static String nombreFA = "Almacen.fa";
	static String nombreFO = "Almacen.obj";
	//Fijamos la longitud de el campo c�digo y nombre
	//ya que los String deben tener una longitud fija
	static int lCodigo = 5, lNombre = 20;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int opcion = 0;
		do {
			System.out.println("0-Salir");
			System.out.println("1-Crear fichero aleatorio a partir de "
					+ "fichero de objetos");
			System.out.println("2-Leer/Mostrar");
			System.out.println("3-Insertar/A�adir material sin duplicados");
			System.out.println("4-Modificar precio material si no est� de baja");
			System.out.println("5-Baja material");
			
			opcion=t.nextInt();t.nextLine();
			switch(opcion) {
				case 1:
					crearFA();
					break;
				case 2:
					mostrarFA();
					break;
				case 3:
					insertarFA();
					break;
				case 4:
					modificarFA();
					break;
				case 5:
					bajaFA();
					break;
			}
			
		}while(opcion!=0);
	}
	private static void bajaFA() {
		// TODO Auto-generated method stub
		//Pedimos el c�digo a moficar
				System.out.println("Introduce c�digo a dar de baja");
				StringBuilder codigo = new StringBuilder(t.nextLine());
				codigo.setLength(lCodigo);		
				//Declaramos fichero
				RandomAccessFile fichero = null;		
				try {
					//Abrimos fichero
					fichero = new RandomAccessFile(nombreFA,"rw");
					//Leemos los registros hasta encontrar el que queremos modificar
					while(true) {
						//Leemos el c�digo
						String codFich = "";
						for(int i=0;i<lCodigo;i++) {
							codFich+=fichero.readChar();
						}
						//Comprobamos si el c�digo le�do es el buscado
						if(codigo.toString().equalsIgnoreCase(codFich)) {
							//Nos desplazamos hasta el campo Alta
							//Nos movemos 48B
							fichero.seek(fichero.getFilePointer()+48);
							//Escribimos Alta = false
							fichero.writeBoolean(false);
							break;
						}
						else {
							//Saltamos 49B para posicionarnos en el siguiente c�digo
							fichero.seek(fichero.getFilePointer()+49);
						}
					}
				} 
				catch (EOFException e) {
					// TODO: handle exception
				}
				catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
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
	private static void modificarFA() {
		// TODO Auto-generated method stub
		
		//Pedimos el c�digo a moficar
		System.out.println("Introduce c�digo a modificar");
		StringBuilder codigo = new StringBuilder(t.nextLine());
		codigo.setLength(lCodigo);		
		//Declaramos fichero
		RandomAccessFile fichero = null;		
		try {
			//Abrimos fichero
			fichero = new RandomAccessFile(nombreFA,"rw");
			//Leemos los registros hasta encontrar el que queremos modificar
			while(true) {
				//Leemos el c�digo
				String codFich = "";
				for(int i=0;i<lCodigo;i++) {
					codFich+=fichero.readChar();
				}
				//Comprobamos si el c�digo le�do es el buscado
				if(codigo.toString().equalsIgnoreCase(codFich)) {
					//Comprobamos si est� de alta
					//Desplazamos 48B (40 nombre + 4Precio + 4Stock)
					// el puntero para leer el campo alta
					fichero.seek(fichero.getFilePointer()+48);
					//Comprobamos si est� de alta
					if(fichero.readBoolean()) {
						//Pedimos el nuevo precio
						System.out.println("Introduce el nuevo precio");
						float nuevoPrecio = t.nextFloat();t.nextLine();
						//Desplazamos el puntero del fichero 
						//9B hacia atr�s (1Alta+4Stock+4Precio)
						fichero.seek(fichero.getFilePointer()-9);
						//Escribimos en el fichero el nuevo precio
						fichero.writeFloat(nuevoPrecio);
						//Finalizamos el bucle
						break;	
					}					
				}
				else {
					//Saltamos 49B para posicionarnos en el siguiente c�digo
					fichero.seek(fichero.getFilePointer()+49);
				}
			}
		} 
		catch (EOFException e) {
			// TODO: handle exception
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	private static void insertarFA() {
		// TODO Auto-generated method stub
		//Pedir c�digo
		Pieza p = new Pieza();
		StringBuilder cadena;
		System.out.println("Introduce el c�digo");
		p.setCodigo(t.nextLine());
		cadena = new StringBuilder(p.getCodigo());
		cadena.setLength(lCodigo);
		//Comprobamos si el c�digo existe
		if(!existePieza(cadena.toString())) {
			p.setCodigo(cadena.toString());
			
			System.out.println("Nombre");
			cadena = new StringBuilder(t.nextLine());
			cadena.setLength(lNombre);
			p.setNombre(cadena.toString());
			
			System.out.println("Precio");
			p.setPrecio(t.nextFloat());t.nextLine();
			
			System.out.println("Stock");
			p.setStock(t.nextInt());t.nextLine();
			
			p.setAlta(true);
			
			//Declaramos fichero
			RandomAccessFile f = null;
			try {
				//Abrimos fichero
				f=new RandomAccessFile(nombreFA,"rw");
				//Desplazamos el apuntador del fichero al final
				f.seek(f.getFilePointer()+f.length());
				//Escribimos el registro
				f.writeChars(p.getCodigo());
				f.writeChars(p.getNombre());
				f.writeFloat(p.getPrecio());
				f.writeInt(p.getStock());
				f.writeBoolean(p.isAlta());
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally {
				if(f!=null) {
					try {
						f.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		else {
			System.out.println("Error: El c�digo ya existe");
		}
		
	}
	private static boolean existePieza(String codigoBuscado) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		
		//Declaramos fichero
		RandomAccessFile f = null;
		
		
		try {
			//Abrimos
			f= new RandomAccessFile(nombreFA,"r");
			
			//Vamos leyendo c�digos hasta encontrar el buscado o llegar al fin
			//de fichero
			while(true && !resultado) {
				//Leemos el c�digo
				String codigo="";
				for(int i=0;i<lCodigo;i++) {
					codigo += f.readChar();
				}
				//Comprobamos si el c�digo le�do es el buscado
				if(codigo.equalsIgnoreCase(codigoBuscado)) {
					resultado = true;
				}
				else {
					//Desplazamos el apuntador del fichero 49 B para 
					//posicionarlo en el siguiente c�digo
					// 49 = 40B del nombre + 4B del precio + 4B del stock + 1B del alta
					f.seek(f.getFilePointer()+49);
				}
			}
		} 
		
		catch (EOFException e) {
			// TODO: handle exception
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		finally {
			if(f!=null) {
				try {
					f.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return resultado;
	}
	private static void mostrarFA() {
		// TODO Auto-generated method stub
		//Declaramos fichero
		RandomAccessFile f = null;
		try {
			//Abrimos fichero para lectura
			f = new RandomAccessFile(nombreFA,"r");
			//Recorremos el fichero hasta el final
			while(true) {
				Pieza p = new Pieza();
				//Leemos el c�digo
				p.setCodigo("");
				for(int i=0;i<lCodigo;i++) {
					p.setCodigo(p.getCodigo()+f.readChar());
				}
				//Leemos el nombre
				p.setNombre("");
				for(int i=0;i<lNombre;i++) {
					p.setNombre(p.getNombre()+f.readChar());
				}
				//Leemos el precio
				p.setPrecio(f.readFloat());
				//Leemos el stock
				p.setStock(f.readInt());
				//Leemos alta
				p.setAlta(f.readBoolean());
				p.mostrar();
			}
		} 
		catch (EOFException e) {
			// TODO: handle exception
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(f!=null) {
				try {
					f.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	private static void crearFA() {
		// TODO Auto-generated method stub
		//Si el fichero aleatorio existe lo borramos
		File fichero = new File(nombreFA);
		if(fichero.exists()) {
			if(!fichero.delete()) {
				System.out.println("Error: No se ha borrado el fichero "+nombreFA);
			}
		}
		//Declaramos ficheros
		ObjectInputStream fO = null;
		RandomAccessFile fA = null;
		
		try {
			
			//Abrimos ficheros
			fO = new ObjectInputStream(new FileInputStream(nombreFO));
			fA = new RandomAccessFile(nombreFA, "rw");
			
			//Leemos los registros de FO y los pasamos a FA
			while(true) {
				Pieza p = (Pieza) fO.readObject();
				//Escribimos el c�digo
				//Creamos un c�digo de 5 car�cteres
				StringBuilder cadena = new StringBuilder(p.getCodigo());
				cadena.setLength(lCodigo);
				fA.writeChars(cadena.toString());
				
				//Escribimos el c�digo
				//Creamos un c�digo de 20 car�cteres
				cadena = new StringBuilder(p.getNombre());
				cadena.setLength(lNombre);
				fA.writeChars(cadena.toString());
				
				//Escribimos el precio
				fA.writeFloat(p.getPrecio());
				//Escribimos el stock
				fA.writeInt(p.getStock());
				//Escribimos alta
				fA.writeBoolean(p.isAlta());
			}
			
		} 
		catch (EOFException e) {
			// TODO: handle exception
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(fO!=null) {
				try {
					fO.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(fA!=null) {
				try {
					fA.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
