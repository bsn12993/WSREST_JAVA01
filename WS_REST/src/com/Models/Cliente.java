package com.Models;

 
 
public class Cliente {

	private int idCliente;
	private String nombre;
	private String telefono;
	
	public Cliente() {
		// TODO Auto-generated constructor stub
	}
	
	

	public Cliente(int idCliente, String nombre, String telefono) {
		super();
		this.idCliente = idCliente;
		this.nombre = nombre;
		this.telefono = telefono;
	}



	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	
}
