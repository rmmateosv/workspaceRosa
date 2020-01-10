package acb;

import java.util.Scanner;


public class Principal {
	public static Scanner t = new Scanner(System.in);
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Modelo acb = new Modelo();
		
		if(acb.getEm()!=null) {
			int opcion = 0;
			Partido pSel=null;
			do {
				System.out.println("0-Salir");
				System.out.println("1-Seleccionar Partido");
				System.out.println("2-Registrar Acción");
				System.out.println("3-Anular Acción");
				System.out.println("4-Borrar Partido");
				System.out.println("5-Mostrar Estadística");
				opcion = t.nextInt();t.nextLine();
				
				
				switch(opcion) {
				case 1:
					acb.mostrarPartidos();
					System.out.println("Introduce código partido:");
					pSel = new Partido();
					pSel.setCodigo(t.nextInt());t.nextLine();
					pSel = acb.obtenerPartido(pSel);
					
					if(pSel!=null) {
						System.out.println("Partido seleccionado");
					}
					else {
						System.out.println("Error, no existe el partido");
					}
					break;
				case 2:
					acb.mostrarTiposAccion();
					System.out.println("Introduce tipo de acción");
					TipoAccion tipoA = new TipoAccion();
					tipoA.setTipo(t.nextLine());
					tipoA = acb.obtenerTipoAccion(tipoA);
					if(tipoA!=null) {
						acb.mostrarJugadores(pSel);
						System.out.println("Introduce código jugador");
						Jugador j = new Jugador();
						j.setCodigo(t.nextInt());t.nextLine();
						j= acb.obtenerJugador(j);
						if(j!=null) {
							if(j.getEquipo()!=pSel.getLocal() &&
								j.getEquipo()!=pSel.getVisitante()) {
								System.out.println("Error, el juador no es de ningún equipo del partido");
							}
							else {
								if(!acb.registrarAccion(pSel,j,tipoA)) {
									System.out.println("Error al registrar la acción");
								}
							}
						}
						else {
							System.out.println("Error, jugador no existe");
						}
					}
					else {
						System.out.println("Error, no existe el tipo de acción");
					}
				}
			}while(opcion!=0);
			
			acb.cerrar();
		}
	}

}
