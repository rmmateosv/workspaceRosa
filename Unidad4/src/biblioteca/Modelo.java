package biblioteca;

import java.util.Calendar;
import java.util.Date;
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

	public void mostrarLibros() {
		// TODO Auto-generated method stub
		try {
			Query consulta = em.createQuery("from Libro");
			List<Libro> r = consulta.getResultList();
			for(Libro l : r) {
				l.mostrar(false);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

	public boolean modificarLibro(Libro l) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		EntityTransaction t = null;
		try {
			t=em.getTransaction();
			t.begin();
			//em.merge(l);
			t.commit();
			resultado=true;
					
		} catch (Exception e) {
			// TODO: handle exception
			t.rollback();
			e.printStackTrace();
		}
		return resultado;
	}

	public void mostrarSocios() {
		// TODO Auto-generated method stub
		try {
			Query consulta = em.createQuery("from Socio");
			List<Socio> r = consulta.getResultList();
			for(Socio s : r) {
				s.mostrar(false);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public boolean crearPrestamo(Socio s, Libro l) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		EntityTransaction t = null;
		try {
			//Calculamos la fecha de devolucion
			Calendar c = Calendar.getInstance();
			c.setTime(new Date());
			c.add(Calendar.DAY_OF_MONTH, 15);
			Prestamo p = new Prestamo(
					new PrestamoClave(l, s, new Date()), 
					c.getTime(), 
					null);
			//Otra forma de sumar d�as a una fecha. Pasar el n� de dias
			//a miliseg. 15 d�as = 1296000000 ms
			/*Prestamo p = new Prestamo(
					new PrestamoClave(l, s, new Date()), 
					new Date((new Date()).getTime()+1296000000), 
					null);*/
			t=em.getTransaction();
			t.begin();
			em.persist(p);
			//Disminu�mos el n� de ejemplares
			l.setNumEjemplares(l.getNumEjemplares()-1);
			//limpiamos cach� de em para que se actualicen los cambios
			t.commit();
			em.clear();
			resultado=true;
			
		} catch (Exception e) {
			// TODO: handle exception
			t.rollback();
			e.printStackTrace();
		}
		return resultado;
	}

	public long obtenerPrestPtes(Socio s) {
		// TODO Auto-generated method stub
		long resultado = 0;
		try {
			Query consulta = em.createQuery(
					"select count(*) from Prestamo "
					+ "where id.socio = :id and "
					+ "fechaDevolReal is null");
			consulta.setParameter("idXX", s);
			List<Long> r = consulta.getResultList();
			
			resultado = r.get(0);
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean devolverPrestamo(Prestamo p) {
		// TODO Auto-generated method stub
		boolean resultado = true;
		EntityTransaction t = null;
		try {
			t = em.getTransaction();
			t.begin();
			p.setFechaDevolReal(new Date());
			
			p.getId().getLibro().setNumEjemplares
			(p.getId().getLibro().getNumEjemplares()+1);
			
			//Calculamos la fecha de sanci�n
			if(p.getFechaDevolPrevista().getTime()<p.getFechaDevolReal().getTime()) {
				p.getId().getSocio().setSancionado(true);
			}
			
			t.commit();
			resultado=true;
			em.clear();
		}
		catch (Exception e) {
			// TODO: handle exception
			t.rollback();
			e.printStackTrace();
		}
		return resultado;
	}

	public void mostrarPrestamosPtes() {
		// TODO Auto-generated method stub
		try {
			Query consulta = 
					em.createQuery("select count(*), id.socio.nif, id.socio.nombre "
							+ "from Prestamo "
							+ "where fechaDevolReal is null "
							+ "group by id.socio");
			List<Object[]> r = consulta.getResultList();
			for(Object[] o : r) {
				System.out.println("N� Prestamos Ptes:"+ (Long) o[0]+
						"\tNIF:" + (String) o[1] +
						"\tNombre Socio:" + (String) o[2]);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
}
