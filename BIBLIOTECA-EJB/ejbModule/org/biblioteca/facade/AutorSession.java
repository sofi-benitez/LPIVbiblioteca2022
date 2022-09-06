package org.biblioteca.facade;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.biblioteca.entidad.Autor;

@Stateless
public class AutorSession {

	@PersistenceContext
	EntityManager em;

    public AutorSession() {
    }
    public List<Autor> buscarTodos() throws Exception {
		String jpql = "SELECT c FROM Autor c ORDER BY c.codigo";
		List<Autor> autores = (List<Autor>) em.createQuery(jpql, Autor.class).getResultList();
		return autores;
	}
    
    public List<Autor> ConsultarPorNombre(String nombre) throws Exception {
		String jpql = "SELECT c FROM Autor c WHERE c.nombre LIKE :n ORDER BY c.codigo";
		List<Autor> autores = (List<Autor>) em.createQuery(jpql, Autor.class).getResultList();
		return autores;
	}

	public Autor buscarPorCodigo(Integer codigo) throws Exception {
		return em.find(Autor.class, codigo);
	}

    public Autor actualizar(Autor autorAct) throws Exception {
    	Autor autor = buscarPorCodigo(autorAct.getCodigo()); 
	    if (autor == null) { 
	    	autorAct.setCodigo(null);
		    em.persist(autorAct);
		    em.refresh(autorAct);
	    } else {
	    	autorAct = em.merge(autorAct);
	    }
	    return autorAct;
    }

	public void eliminar(Integer codigo) throws Exception {
		Autor ciu = buscarPorCodigo(codigo);
		if (ciu != null) {
			em.remove(ciu);
		}
	}
}
