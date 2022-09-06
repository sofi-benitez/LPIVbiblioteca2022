package org.biblioteca.facade;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.biblioteca.entidad.Usuario;


@Stateless
public class UsuarioSession {
	
	@PersistenceContext
	EntityManager em;
	
    public UsuarioSession() {
    }
    public List<Usuario> buscarTodos() throws Exception {
		String jpql = "SELECT c FROM Usuario c ORDER BY c.codigo";
		List<Usuario> usuarios = (List<Usuario>) em.createQuery(jpql, Usuario.class).getResultList();
		return usuarios;
	}

	public Usuario buscarPorCodigo(Integer codigo) throws Exception {
		return em.find(Usuario.class, codigo);
	}

    public Usuario actualizar(Usuario usuarioAct) throws Exception {
    	Usuario usuario = buscarPorCodigo(usuarioAct.getCodigo()); 
	    if (usuario == null) { 
	    	usuarioAct.setCodigo(null);
		    em.persist(usuarioAct);
		    em.refresh(usuarioAct);
	    } else {
	    	usuarioAct = em.merge(usuarioAct);
	    }
	    return usuarioAct;
    }

	public void eliminar(Integer codigo) throws Exception {
		Usuario ciu = buscarPorCodigo(codigo);
		if (ciu != null) {
			em.remove(ciu);
		}
	}
}
