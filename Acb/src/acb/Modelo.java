package acb;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

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
	
}
