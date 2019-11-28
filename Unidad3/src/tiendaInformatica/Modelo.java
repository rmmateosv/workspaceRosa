package tiendaInformatica;

import java.io.File;

import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.CollectionManagementService;
import org.xmldb.api.modules.XPathQueryService;

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
			//Nos conectamos a la colecci�n principal del servidor
			//porque es posible que la colecci�n TiendaInformatica no exista
			Collection padre = DatabaseManager.getCollection(url,usuario,clave);
			//Obtenemos la coleccion TiendaInformatica
			col = padre.getChildCollection(nombreC);
			if(col==null) {
				//Creamos la coleccion TiendaInformatica en la colecci�n padre
				CollectionManagementService servicio = 
					(CollectionManagementService) 
					padre.getService("CollectionManagementService", "1.0");
				col=servicio.createCollection(nombreC);
				//Cargamos los ficheros xml vac�os en la colecci�n
				//Fichero Piezas.xml
				File fichero = new File("piezas.xml");
				//Creamos el recurso
				Resource recurso = 
						col.createResource(fichero.getName(), "XMLResource");
				//Asignamos el fichero al recurso
				recurso.setContent(fichero);
				//Guardamos el recurso en la colecci�n
				col.storeResource(recurso);
				//Fichero Ordenadors.xml
				fichero = new File("ordenadores.xml");
				//Creamos el recurso
				recurso = 
						col.createResource(fichero.getName(), "XMLResource");
				//Asignamos el fichero al recurso
				recurso.setContent(fichero);
				//Guardamos el recurso en la colecci�n
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
	public boolean insertarPieza(Pieza p) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		
		p.setCodigo(obtenerCodigoPieza());
		p.setAlta(true);
		try {
			XPathQueryService consulta = (XPathQueryService) col.getService("XPathQueryService", "1.0");
			consulta.query("update insert "
					+ "<pieza codigo='"+p.getCodigo()+"' alta='"+p.isAlta()+"'>"
							+ "<nombre>"+p.getNombre()+"</nombre>"
							+ "<stock>"+p.getStock()+"</stock>"
							+ "<precio>"+p.getPrecio()+"</precio>"
					+ "</pieza>"
					+ "into /piezas");
		} catch (XMLDBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}
	private int obtenerCodigoPieza() {
		// TODO Auto-generated method stub
		int resultado = 1;
		try {
			XPathQueryService consulta = 
					(XPathQueryService) 
					col.getService("XPathQueryService", "1.0");
			ResourceSet r = consulta.query("string(/piezas/pieza[last()]/@codigo)");
			ResourceIterator i = r.getIterator();
			if(i.hasMoreResources()) {
				resultado = Integer.parseInt(
						i.nextResource().getContent().toString())+1;
			}
		} catch (XMLDBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}
}
