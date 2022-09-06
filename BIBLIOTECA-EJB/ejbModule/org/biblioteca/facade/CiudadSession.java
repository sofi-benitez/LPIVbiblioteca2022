package org.biblioteca.facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.biblioteca.entidad.Ciudad;

@Stateless

public class CiudadSession {

	@PersistenceContext
	EntityManager em;

	public CiudadSession() {
	}

	public List<Ciudad> buscarTodos() throws Exception {
		String jpql = "SELECT c FROM Ciudad c ORDER BY c.codigo";
		List<Ciudad> ciudades = (List<Ciudad>) em.createQuery(jpql, Ciudad.class).getResultList();
		return ciudades;
	}

	public Ciudad buscarPorCodigo(Integer codigo) throws Exception {
		return em.find(Ciudad.class, codigo);
	}

    public Ciudad actualizar(Ciudad ciudadAct) throws Exception {
	    Ciudad ciudad = buscarPorCodigo(ciudadAct.getCodigo()); 
	    if (ciudad == null) { 
		    ciudadAct.setCodigo(null);
		    em.persist(ciudadAct);
		    em.refresh(ciudadAct);
	    } else {
	    	ciudadAct = em.merge(ciudadAct);
	    }
	    return ciudadAct;
    }

	public void eliminar(Integer codigo) throws Exception {
		Ciudad ciu = buscarPorCodigo(codigo);
		if (ciu != null) {
			em.remove(ciu);
		}
	}

}
