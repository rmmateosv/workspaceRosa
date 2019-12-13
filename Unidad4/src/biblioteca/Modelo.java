package biblioteca;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class Modelo {
	private EntityManager em = null;

	public Modelo() {
		try {
			
			em = Persistence.createEntityManagerFactory("BIBLIOTECA").createEntityManager();

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
		try {
			em.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public Socio obtenerSocio(Socio s) {
		// TODO Auto-generated method stub
		Socio resultado= null;
		try {
			//Recuperamos de la bd el socio buscado
			Query consulta = em.createQuery("from Socio "
					+ "where nif = :nif");
			consulta.setParameter("nif", s.getNif());
			List<Socio> r = consulta.getResultList();
			if(!r.isEmpty()) {
				resultado=r.get(0);
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		return resultado;
	}

	public boolean crearSocio(Socio s) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		EntityTransaction t = null;
		try {
			t = em.getTransaction();
			t.begin();
			em.persist(s);
			t.commit();
			resultado=true;
		}
		catch (Exception e) {
			// TODO: handle exception
			t.rollback();
			e.printStackTrace();
		}
		return resultado;
	}

	public Libro obtenerLibro(Libro l) {
		// TODO Auto-generated method stub
		Libro resultado=null;
		try {
			resultado = em.find(Libro.class, l.getIsbn());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean altaLibro(Libro l) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		EntityTransaction t = null;
		try {
			t = em.getTransaction();
			t.begin();
			em.persist(l);
			t.commit();
			resultado=true;
		}
		catch (Exception e) {
			// TODO: handle exception
			t.rollback();
			e.printStackTrace();
		}
		return resultado;
	}
}
