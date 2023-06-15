package com.cdc.rce.clientes.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.cdc.rce.clientes.beans.Cliente;

public class ClienteMapper implements RowMapper<Cliente> {

	@Override
	public Cliente mapRow(ResultSet rs, int rowNum) throws SQLException {
		Cliente cliente = new Cliente();
		StringBuilder nombre = new StringBuilder();

		if (rs != null && rowNum >= 0) {

			try {

				if (rs.getString("EMAIL") != null && !"".equals(rs.getString("EMAIL")))
				cliente.setEmail(rs.getString("EMAIL"));

				if (rs.getString("PRIMERNOMBRE") != null && !"".equals(rs.getString("PRIMERNOMBRE")))
					nombre.append(rs.getString("PRIMERNOMBRE"));
				
				if (rs.getString("SEGUNDONOMBRE") != null && !"".equals(rs.getString("SEGUNDONOMBRE")))
					nombre.append(" " + rs.getString("SEGUNDONOMBRE"));

				if (rs.getString("APELLIDOPATERNO") != null && !"".equals(rs.getString("APELLIDOPATERNO")))
					nombre.append(" " + rs.getString("APELLIDOPATERNO"));
				
				if (rs.getString("APELLIDOMATERNO") != null && !"".equals(rs.getString("APELLIDOMATERNO")))
					nombre.append(" " + rs.getString("APELLIDOMATERNO"));
				
				cliente.setNombre(nombre.toString());

				


			} catch (Exception e) {

				e.printStackTrace();
			}

		}

		return cliente;
	}

}
