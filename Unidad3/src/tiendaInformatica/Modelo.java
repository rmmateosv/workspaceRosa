package tiendaInformatica;

import java.io.File;

import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.CollectionManagementService;

public class Modelo {

	Collection col = null;
	String url="xmldb:exist://localhost:8080/exist/xmlrpc/db", 
		   usuario="admin", 
		   clave="admin",
		   nombreC="TiendaInformatica";
	public Modelo() {
		
		Class driver;
		try {
			//Cargar el driver
			driver = Class.forName("org.exist.xmldb.DatabaseImpl");
			//Crear una instancia de la BD
			Database db = (Database) driver.newInstance();
			//Registrar la BD
			DatabaseManager.registerDatabase(db);
			//Nos conectamos a la colección principal del servidor
			//porque es posible que la colección TiendaInformatica no exista
			Collection padre = DatabaseManager.getCollection(url,usuario,clave);
			//Obtenemos la coleccion TiendaInformatica
			col = padre.getChildCollection(nombreC);
			if(col==null) {
				//Creamos la coleccion TiendaInformatica en la colección padre
				CollectionManagementService servicio = 
					(CollectionManagementService) 
					padre.getService("CollectionManagementService", "1.0");
				col=servicio.createCollection(nombreC);
				//Cargamos los ficheros xml vacíos en la colección
				//Fichero Piezas.xml
				File fichero = new File("piezas.xml");
				//Creamos el recurso
				Resource recurso = 
						col.createResource(fichero.getName(), "XMLResource");
				//Asignamos el fichero al recurso
				recurso.setContent(fichero);
				//Guardamos el recurso en la colección
				col.storeResource(recurso);
				//Fichero Ordenadors.xml
				fichero = new File("ordenadores.xml");
				//Creamos el recurso
				recurso = 
						col.createResource(fichero.getName(), "XMLResource");
				//Asignamos el fichero al recurso
				recurso.setContent(fichero);
				//Guardamos el recurso en la colección
				col.storeResource(recurso);
			}
			
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XMLDBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public Collection getCol() {
		return col;
	}
	public void setCol(Collection col) {
		this.col = col;
	}
	
	public void cerrar() {
		try {
			col.close();
		} catch (XMLDBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
