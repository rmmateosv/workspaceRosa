package acb;

import java.util.Scanner;

public class Principal {
	public static Scanner t = new Scanner(System.in);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Modelo acb = new Modelo();

		if (acb.getEm() != null) {
			int opcion = 0;
			Partido pSel = null;
			do {
				System.out.println("0-Salir");
				System.out.println("1-Seleccionar Partido");
				System.out.println("2-Registrar Acci�n");
				System.out.println("3-Anular Acci�n");
				System.out.println("4-Borrar Partido");
				System.out.println("5-Mostrar Estad�stica");
				opcion = t.nextInt();
				t.nextLine();

				switch (opcion) {
				case 1:
					acb.mostrarPartidos();
					System.out.println("Introduce c�digo partido:");
					pSel = new Partido();
					pSel.setCodigo(t.nextInt());
					t.nextLine();
					pSel = acb.obtenerPartido(pSel);

					if (pSel != null) {
						System.out.println("Partido seleccionado");
					} else {
						System.out.println("Error, no existe el partido");
					}
					break;
				case 2:
					pSel = acb.obtenerPartido(pSel);
					if (pSel != null) {
						acb.mostrarTiposAccion();
						System.out.println("Introduce tipo de acci�n");
						TipoAccion tipoA = new TipoAccion();
						tipoA.setTipo(t.nextLine());
						tipoA = acb.obtenerTipoAccion(tipoA);
						if (tipoA != null) {
							acb.mostrarJugadores(pSel);
							System.out.println("Introduce c�digo jugador");
							Jugador j = new Jugador();
							j.setCodigo(t.nextInt());
							t.nextLine();
							j = acb.obtenerJugador(j);
							if (j != null) {
								if (j.getEquipo() != pSel.getLocal() && j.getEquipo() != pSel.getVisitante()) {
									System.out.println("Error, el juador no es de ning�n equipo del partido");
								} else {
									if (!acb.registrarAccion(pSel, j, tipoA)) {
										System.out.println("Error al registrar la acci�n");
									}
								}
							} else {
								System.out.println("Error, jugador no existe");
							}
						} else {
							System.out.println("Error, no existe el tipo de acci�n");
						}
					} else {
						System.out.println("Debes seleccionar partido");
					}
					break;
				case 3:
					//pSel = acb.obtenerPartido(pSel);
					if (pSel != null) {
						System.out.println("Acciones del partido:");
						for (Accion a : pSel.getAcciones()) {
							a.mostrar();
						}
						System.out.println("Introduce c�digo de acci�n a anular");
						int codigo = t.nextInt();t.nextLine();
						//comprobamos que la accci�n existe
						for (Accion a : pSel.getAcciones()) {
							if(a.getCodigo()==codigo) {
								if(!acb.anularAccion(a)) {
									System.out.println("Error al anular la acci�n");
								}
								break;
							}
						}
						
					} else {
						System.out.println("Debes seleccionar partido");
					}
					break;
				case 4:
					acb.mostrarPartidos();
					Partido p = new Partido();
					System.out.println("Introduce c�digo");
					p.setCodigo(t.nextInt());t.nextLine();
					p=acb.obtenerPartido(p);
					if(p!=null) {
						if(p.getAcciones().size()>0) {
							System.out.println("Error, el partido tiene acciones");
						}
						else {
							if(!acb.borrarPartido(p)) {
								System.out.println("Error al borrar el partido4");
							}
						}
						
					}
					else {
						System.out.println("Error, el partido no existe");
					}
					break;
				case 5:
					if(pSel!=null) {
						acb.mostrarEstadistica(pSel);
					}
					break;
					
				
				}
			} while (opcion != 0);

			acb.cerrar();
		}
	}

}
