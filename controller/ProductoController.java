package com.alura.jdbc.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alura.jdbc.factory.ConnectionFactory;


public class ProductoController {

	// Clase ProductoController
public int modificar(String nombre, String descripcion, Integer cantidad, Integer id) throws SQLException {
	    ConnectionFactory factory = new ConnectionFactory();
	    
	    Connection con = factory.recuperaConexico();
	    
	    Statement statement = con.createStatement();
	    statement.execute("UPDATE PRODUCTO SET "
	            + " nombre = '" + nombre + "'"
	            + ", descripcion = '" + descripcion + "'"
	            + ", cantidad = " + cantidad
	            + " WHERE id = " + id);

	    int updateCount = statement.getUpdateCount();

	    con.close();
		return updateCount;   

	}
	
	
	public int eliminar(Integer id) throws SQLException {
		Connection con = new ConnectionFactory().recuperaConexico();
		
		Statement statement = con.createStatement();
		
		statement.execute("DELETE FROM PRODUCTO WHERE ID = " + id);
		
		return statement.getUpdateCount();
	}

	public List<Map<String, String>> listar() throws SQLException {
		Connection con = new ConnectionFactory().recuperaConexico();
		
		Statement statement = con.createStatement();
		
	
		statement.execute("SELECT id, nombre, descripcion, cantidad FROM producto");
		
		ResultSet resultSet = statement.getResultSet();
        List<Map<String, String>> resultado = new ArrayList<>();
		
		while (resultSet.next()) {
			Map<String, String> fila = new HashMap<>();
			fila.put("id", String.valueOf(resultSet.getInt("id")));
			fila.put("nombre", String.valueOf(resultSet.getString("nombre")));
			fila.put("descripcion", String.valueOf(resultSet.getString("descripcion")));
			fila.put("cantidad", String.valueOf(resultSet.getInt("cantidad")));
			
			resultado.add(fila);
			
		}
				
		con.close();
				
		
		return resultado;
	}

    public void guardar(Map<String, String> producto) throws SQLException {
		Connection con = new ConnectionFactory().recuperaConexico();
		
		Statement statement = con.createStatement();
		
		statement.execute("INSERT INTO PRODUCTO (nombre, descripcion, cantidad)"
				+ " VALUES('" + producto.get("nombre") + "','"
				+ producto.get("descripcion") + "'," 
				+ producto.get("cantidad") + ")", Statement.RETURN_GENERATED_KEYS);
		
		ResultSet resultSet = statement.getGeneratedKeys();
		
		while (resultSet.next()) {
			System.out.println(
					String.format(
							"Fue Insertado El Producto id %d",
							resultSet.getInt(1)));
			
		}
	}

}
