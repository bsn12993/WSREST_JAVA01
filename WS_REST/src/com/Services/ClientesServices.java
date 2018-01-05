package com.Services;

import java.util.ArrayList;

import javax.websocket.server.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.Models.Cliente;
import com.Models.mensajeRespuesta;
import com.Repository.ClienteRepository;
import com.google.gson.Gson;

@Path("/clientes")
public class ClientesServices {
	
	Gson gson=new Gson();
	ClienteRepository clienteRepository=new ClienteRepository();

	//URI
	// /WS_REST/ApiREST/clientes
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public String getClientes(){
		return gson.toJson(clienteRepository.getAllCustoms());
	}
	
	//URI
	// /WS_REST/ApiREST/clientes
	@POST
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	public String postCliente(String cli){
		Cliente cliente=gson.fromJson(cli, Cliente.class);
		boolean resultado=clienteRepository.createCustom(cliente);
		mensajeRespuesta mensajeRespuesta=null;
		if(!resultado){
			mensajeRespuesta=new mensajeRespuesta();
			mensajeRespuesta.setEstatus("Error");
			mensajeRespuesta.setMensaje("No se encontraron resultados");
			mensajeRespuesta.setClientes(new ArrayList<Cliente>());
			//return Response.status(400).entity(mensajeRespuesta.getMensaje().toString()).build();
			return gson.toJson(mensajeRespuesta);
		}else{
			mensajeRespuesta=new mensajeRespuesta();
			mensajeRespuesta.setEstatus("OK");
			mensajeRespuesta.setMensaje("Se ha registrado un cliente");
			mensajeRespuesta.setClientes(new ArrayList<Cliente>());
			//return Response.status(200).entity("Se ha registrado un cliente").build();
			return gson.toJson(mensajeRespuesta);
		}
	}
	
	//URI
	// /WS_REST/ApiREST/clientes
	@PUT
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({MediaType.APPLICATION_JSON})
    public String updateEmployee(String cli) {
		Cliente cliente=gson.fromJson(cli, Cliente.class);
		boolean resultado=clienteRepository.updateCustom(cliente);
		mensajeRespuesta mensajeRespuesta=null;
		if(!resultado){
			mensajeRespuesta=new mensajeRespuesta();
			mensajeRespuesta.setEstatus("Error");
			mensajeRespuesta.setMensaje("No se encontraron resultados");
			mensajeRespuesta.setClientes(new ArrayList<Cliente>());
			//return Response.status(400).entity(mensajeRespuesta.getMensaje().toString()).build();
			return gson.toJson(mensajeRespuesta);
		}else{
			mensajeRespuesta=new mensajeRespuesta();
			mensajeRespuesta.setEstatus("OK");
			mensajeRespuesta.setMensaje("Se ha actualizado el cliente "+cliente.getIdCliente()+" correctamente");
			mensajeRespuesta.setClientes(new ArrayList<Cliente>());
			//return Response.status(200).entity("Se ha registrado un cliente").build();
			return gson.toJson(mensajeRespuesta);
		}
    }
	
	//URI
	// /WS_REST/ApiREST/clientes
	@DELETE
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public String deleteEmployee(String id) {
		Cliente cliente=gson.fromJson(id, Cliente.class);
		boolean resultado=clienteRepository.deleteCustom(cliente.getIdCliente());
		mensajeRespuesta mensajeRespuesta=null;
		if(!resultado){
			mensajeRespuesta=new mensajeRespuesta();
			mensajeRespuesta.setEstatus("Error");
			mensajeRespuesta.setMensaje("Ocurrio un error al ejecutar la operacion");
			mensajeRespuesta.setClientes(new ArrayList<Cliente>());
			//return Response.status(400).entity(mensajeRespuesta.getMensaje().toString()).build();
			return gson.toJson(mensajeRespuesta);
		}else{
			mensajeRespuesta=new mensajeRespuesta();
			mensajeRespuesta.setEstatus("OK");
			mensajeRespuesta.setMensaje("Se elimino el cliente "+cliente.getIdCliente()+" correctamente");
			mensajeRespuesta.setClientes(new ArrayList<Cliente>());
			//return Response.status(200).entity("Se ha registrado un cliente").build();
			return gson.toJson(mensajeRespuesta);
		}
    }
	
}
