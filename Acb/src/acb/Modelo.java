package acb;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class Modelo {
	private EntityManager em = null;

	public Modelo() {
		try {
			
			em = Persistence.createEntityManagerFactory("ACB").createEntityManager();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
	public void cerrar() {
		em.close();
	}

	public void mostrarPartidos() {
		try {
			// TODO Auto-generated method stub
			Query consulta = em.createQuery("from Partido");
			
			List<Partido> r = consulta.getResultList();
			for(Partido p: r) {
				p.mostrar();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

	public Partido obtenerPartido(Partido pSel) {
		// TODO Auto-generated method stub
		return em.find(Partido.class, pSel.getCodigo());
	}

	public void mostrarTiposAccion() {
		// TODO Auto-generated method stub
		try {
			// TODO Auto-generated method stub
			Query consulta = em.createQuery("from TipoAccion");
			
			List<TipoAccion> r = consulta.getResultList();
			for(TipoAccion t: r) {
				t.mostrar();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public TipoAccion obtenerTipoAccion(TipoAccion tipoA) {
		// TODO Auto-generated method stub
		return em.find(TipoAccion.class, tipoA.getTipo());
	}

	public void mostrarJugadores(Partido pSel) {
		// TODO Auto-generated method stub
		try {
			// TODO Auto-generated method stub
			Query consulta = em.createQuery("from Jugador "
					+ "where equipo.nombre = :eLocal or equipo = :eVisi");
			consulta.setParameter("eLocal", pSel.getLocal().getNombre());
			consulta.setParameter("eVisi", pSel.getVisitante());
			
			List<Jugador> r = consulta.getResultList();
			for(Jugador j: r) {
				j.mostrar();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public Jugador obtenerJugador(Jugador j) {
		// TODO Auto-generated method stub
		return em.find(Jugador.class, j.getCodigo());
	}

	public boolean registrarAccion(Partido pSel, Jugador j, TipoAccion tipoA) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		EntityTransaction t = null;
		try {
			t= em.getTransaction();
			t.begin();
			
			Accion a = new Accion(pSel, tipoA, j, false);
			em.persist(a);
			t.commit();
			resultado= true;
			em.clear();
		}
		catch (Exception e) {
			// TODO: handle exception
			t.rollback();
			e.printStackTrace();
		}
		return resultado;
	}
	
}
