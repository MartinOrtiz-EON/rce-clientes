package com.cdc.rce.clientes.mappers;

import com.cdc.rce.clientes.beans.Cliente;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientesTramiteInconclusoMapper implements RowMapper<Cliente> {

	@Override
	public Cliente mapRow(ResultSet rs, int rowNum) throws SQLException {
		Cliente cliente = new Cliente();

		if (rs != null && rowNum >= 0) {

			try {
				if (rs.getString("email") != null && !"".equals(rs.getString("EMAIL")))
					cliente.setEmail(rs.getString("EMAIL"));

				if (rs.getString("nombreCompleto") != null && !"".equals(rs.getString("nombreCompleto")))
					cliente.setNombre(rs.getString("nombreCompleto"));

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return cliente;
	}

}
