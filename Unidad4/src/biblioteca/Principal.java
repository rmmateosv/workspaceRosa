package biblioteca;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Modelo biblioteca = new Modelo();
		if(biblioteca.getEm()!=null) {
			System.out.println("Conectadoooooo");
		}
		biblioteca.cerrar();
	}

}
