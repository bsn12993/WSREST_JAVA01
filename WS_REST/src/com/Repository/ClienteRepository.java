package com.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import com.ConexionBD.SQLServerConnection;
import com.Models.Cliente;
import com.Models.mensajeRespuesta;

public class ClienteRepository {
	
	ResultSet rs=null;
	PreparedStatement ps=null;
	
	Connection con=new SQLServerConnection("jdbc:jtds:sqlserver://localhost:1433;DatabaseName=BDClientes", "sa", "123", "net.sourceforge.jtds.jdbc.Driver").getConnection();
	
	
	public mensajeRespuesta getAllCustoms(){
		mensajeRespuesta respuesta=new mensajeRespuesta();
		Cliente cliente=null;
		List<Cliente> lstCliente=null;
		StringBuilder sql=new StringBuilder();
		sql.append("Select * from Clientes");
		try {
			ps=con.prepareStatement(sql.toString());
			rs=ps.executeQuery();
			if(rs!=null){
				lstCliente=new ArrayList<>();
				while(rs.next()){
					cliente=new Cliente();
					cliente.setIdCliente(rs.getInt("IdCliente"));
					cliente.setNombre(rs.getString("Nombre"));
					cliente.setTelefono(rs.getString("Telefono"));
					lstCliente.add(cliente);
				}
				respuesta.setMensaje("");
				respuesta.setEstatus("OK");
				respuesta.setClientes(lstCliente);
				System.out.println(respuesta);
			}else{
				lstCliente=new ArrayList<>();
				respuesta.setMensaje("No se encontraron resultados");
				respuesta.setEstatus("Error");
				respuesta.setClientes(lstCliente);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		finally{
			try {
				con.close();
				rs.close();
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return respuesta;
	}
	
	public boolean createCustom(Cliente cliente){
		boolean resultado=false;
		StringBuilder sql=new StringBuilder();
		sql.append("INSERT INTO Clientes (Nombre,Telefono) VALUES(?,?)");
		try{
			ps=con.prepareStatement(sql.toString());
			ps.setString(1, cliente.getNombre());
			ps.setInt(2, Integer.parseInt(cliente.getTelefono()));
			int respuesta=ps.executeUpdate();
			if(respuesta!=0){
				resultado = true;
			}
		}catch(SQLException e){
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			resultado = false;
		}
		return resultado;
	}
	
	public boolean updateCustom(Cliente cliente){
		boolean resultado=false;
		StringBuilder sql=new StringBuilder();
		sql.append("UPDATE Clientes SET Nombre = ? ,Telefono = ? WHERE IdCliente = ?");
		try{
			ps=con.prepareStatement(sql.toString());
			ps.setString(1, cliente.getNombre());
			ps.setInt(2, Integer.parseInt(cliente.getTelefono()));
			ps.setInt(3, cliente.getIdCliente());
			int respuesta=ps.executeUpdate();
			if(respuesta!=0){
				resultado = true;
			}
		}catch(Exception e){
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			resultado = false;
		}
		return resultado;
	}
	
	public boolean deleteCustom(int id){
		boolean resultado=false;
		StringBuilder sql=new StringBuilder();
		sql.append("DELETE FROM Clientes WHERE IdCliente = ?");
		try{
			ps=con.prepareStatement(sql.toString());
			ps.setInt(1, id);
			int respuesta=ps.executeUpdate();
			if(respuesta!=0){
				resultado = true;
			}
		}catch(Exception e){
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			resultado = false;
		}
		return resultado;
	}
	
}
