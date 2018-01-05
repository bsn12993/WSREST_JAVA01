package com.ConexionBD;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLServerConnection implements IConexion{

	private Connection cn=null;
	private String url;
	private String usuario;
	private String password;
	private String driver;
	
	
	
	public SQLServerConnection(String url, String usuario, String password, String driver) {
		super();
		this.url = url;
		this.usuario = usuario;
		this.password = password;
		this.driver = driver;
	}



	@Override
	public Connection getConnection() {
		try {
			Class.forName(this.driver);
			cn=DriverManager.getConnection(this.url,this.usuario,this.password);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cn;
	}

}
