package com.alura.jdbc.pruebas;

import java.sql.Connection;
import java.sql.SQLException;

import com.alura.jdbc.factory.ConnectionFactory;

public class PruebaPoolDeConnexiones {
	
	public static void main(String[] args) throws SQLException {
		ConnectionFactory connectionFactory = new ConnectionFactory(); 
		
	    for (int i = 0; i < 20; i++) {
			Connection conexion = connectionFactory.recuperaConexico();
			
			System.out.println("abriendo conexion de numero" + (i + 1));
		}	
	}

}
