package org.biblioteca.abm.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.biblioteca.entidad.Usuario;
import org.biblioteca.facade.UsuarioSession;


@Path("usuario")
public class UsuarioRestService {
	@EJB
	UsuarioSession cs;

	// GET http://localhost:8080/PROYECTO-REST/rest/Usuario/list
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/list")
	public List<Usuario> listar() throws Exception {
		return cs.buscarTodos();
	}

	// GET http://localhost:8080/PROYECTO-REST/rest/Usuario/find/n
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/find/{codigo}")
	public Usuario buscar(@PathParam("codigo") Integer codigo) throws Exception {
		return cs.buscarPorCodigo(codigo);
	}

	// PUT http://localhost:8080/PROYECTO-REST/rest/Usuario/update
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/update")
	public Usuario actualizar(Usuario usuario) throws Exception {
		return cs.actualizar(usuario);
	}

	// DELETE http://localhost:8080/PROYECTO-REST/rest/Usuario/delete/n
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/delete/{codigo}")
	public void borrar(@PathParam("codigo") Integer codigo) throws Exception {
		cs.eliminar(codigo);
	}
}
