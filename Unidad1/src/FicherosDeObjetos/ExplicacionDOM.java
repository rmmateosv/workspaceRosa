package FicherosDeObjetos;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class ExplicacionDOM {
	
	static Scanner t = new Scanner(System.in);
	static String nombreFT = "Almacen.txt";
	static String nombreFXML = "Almacen.xml";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int opcion = 0;
		do {
			System.out.println("0-Salir");
			System.out.println("1-Crear fichero xml a partir de fichero texto");
			System.out.println("2-Leer/Mostrar");
			System.out.println("3-Insertar/Añadir material sin duplicados");
			System.out.println("4-Modificar precio material si no está de baja");
			System.out.println("5-Baja material");
			
			opcion=t.nextInt();t.nextLine();
			switch(opcion) {
				case 1:
					crearFicheroXML();
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
			
		}while(opcion!=0);
	}

	private static void crearFicheroXML() {
		// TODO Auto-generated method stub
		//Pedimos el nombre del taller
		System.out.println("Introduce el nombre del taller");
		String nombreTaller = t.nextLine();
		//Declaramos el fichero de texto para leer
		BufferedReader fichero = null;
		
		//Declaramos el arbol DOM para el documento
		Document documento = null;
		
		try {
			//Creamos el constructor de documento
			DocumentBuilder db = 
					DocumentBuilderFactory.newInstance().newDocumentBuilder();
			//Creamos el documento xml vacío
			documento = db.newDocument();
			//Establecemos versión XML
			documento.setXmlVersion("1.0");
			
			//Creamos elementos raiz
			Element raiz = documento.createElement("taller");
			//Asignamos la raiz al árbol
			documento.appendChild(raiz);
			
			//Creamos el elemento nombre
			Element nombre = documento.createElement("nombre");
			//Añadimos el elemento nombre a la raiz
			raiz.appendChild(nombre);
			//Creamos el nodo de texto para el nombre del taller
			Text tNombre = documento.createTextNode(nombreTaller);
			//Añadimos el nodo de texto al elemento nombre
			nombre.appendChild(tNombre);
			
			//Creamos el elemento piezas
			Element piezas = documento.createElement("piezas");
			//Añadimos el elemento piezas a la raiz
			raiz.appendChild(piezas);
			
			//Abrimos el fichero de texto para crear los
			// nodos pieza
			fichero = new BufferedReader(new FileReader(nombreFT));
			String linea;
			while((linea=fichero.readLine())!=null) {
				String campos[] = linea.split(";");
				
				//Creamos elemento pieza
				Element pieza = documento.createElement("pieza");
				//Asignamos pieza a piezas
				piezas.appendChild(pieza);
				//Creamos los atributos de pieza
				pieza.setAttribute("codigo", campos[0]);
				pieza.setAttribute("alta", campos[4]);
				
				//Creamos el elemento nombre de pieza
				Element nombrePieza = documento.createElement("nombre");
				//Asignamos nombrePieza a pieza
				pieza.appendChild(nombrePieza);
				//Creamos el nodo texto con el nombre de la pieza
				Text textoNombrePieza = documento.createTextNode(campos[1]);
				nombrePieza.appendChild(textoNombrePieza);
				
				//Creamos el elemento precio de pieza
				Element precio = documento.createElement("precio");
				//Asignamos precio a pieza
				pieza.appendChild(precio);
				//Creamos el nodo texto con el precio de la pieza
				Text textoPrecio = documento.createTextNode(campos[2]);
				precio.appendChild(textoPrecio);
				
				//Creamos el elemento stock de pieza
				Element stock = documento.createElement("stock");
				//Asignamos stock a pieza
				pieza.appendChild(stock);
				//Creamos el nodo texto con el precio de la pieza
				Text textoStock = documento.createTextNode(campos[3]);
				stock.appendChild(textoStock);
			}
			//PAsamos el árbol DOM a fichero
			pasarDOMaXML(documento,nombreFXML);
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
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

	private static void pasarDOMaXML(Document documento, String nombreF) {
		// TODO Auto-generated method stub
		//Crear una fuente/origen con el árbol DOM
		Source fuente = new DOMSource(documento);
		//Crear el destino de la transformación:
		Result ficheroXML = new StreamResult(new File(nombreF));
		//Crear un transformador
		try {
			Transformer t =TransformerFactory.newInstance().newTransformer();
			//Transformar la fuente en el resultado
			t.transform(fuente, ficheroXML);
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
