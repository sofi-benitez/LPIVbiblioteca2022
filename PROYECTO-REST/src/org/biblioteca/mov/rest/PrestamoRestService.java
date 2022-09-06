package org.biblioteca.mov.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.biblioteca.entidad.Prestamo;
import org.mov.facade.PrestamoSession;

@Path("prestamo")

public class PrestamoRestService {
	@EJB
	PrestamoSession cs;

	// GET http://localhost:8080/PROYECTO-REST/rest/prestamo/list
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/list")
	public List<Prestamo> listar() throws Exception {
		return cs.buscarTodos();
	}

	// GET http://localhost:8080/PROYECTO-REST/rest/prestamo/find/n
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/find/{codigo}")
	public Prestamo buscar(@PathParam("codigo") Integer codigo) throws Exception {
		return cs.buscarPorCodigo(codigo);
	}

	// PUT http://localhost:8080/PROYECTO-REST/rest/prestamo/update
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/update")
	public Prestamo actualizar(Prestamo prestamo) throws Exception {
		
		 return cs.actualizar(prestamo, prestamo.getPrestamosLibros());
		
	}

	// DELETE http://localhost:8080/PROYECTO-REST/rest/prestamo/delete/n
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/delete/{numero}")
	public void borrar(@PathParam("numero") Integer numero) throws Exception {
		cs.anular(numero);
	}
}
