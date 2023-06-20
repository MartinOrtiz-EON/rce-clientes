package com.cdc.rce.clientes.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.cdc.rce.clientes.beans.Cliente;
import com.cdc.rce.clientes.dao.GenericDao;
import com.cdc.rce.clientes.dao.IClienteDao;
import com.cdc.rce.clientes.mappers.ClienteMapper;

import oracle.jdbc.OracleTypes;

@Repository
public class ClienteDaoImpl extends GenericDao implements IClienteDao {

	@Value("${db.schema}")
	private String SCHEMA;

	@Value("${db.sp.clientes.rce}")
	private String SP_CLIENTES_RCE;

	@Override
	public List<Cliente> getDatosCliente() {
		List<Cliente> datosCliente = new ArrayList<>();

		try {
			SimpleJdbcCall jdbcCall = new SimpleJdbcCall(getDataSource())
					.withSchemaName(SCHEMA)
					.withProcedureName(SP_CLIENTES_RCE)
					.declareParameters(
							new SqlOutParameter("OUT_DATOS_CLIENTE", OracleTypes.CURSOR, new ClienteMapper()),
							new SqlOutParameter("OUT_ERROR", OracleTypes.VARCHAR));

			Map<String, Object> map = jdbcCall.execute();

			String error = (String) map.get("OUT_ERROR");

			@SuppressWarnings("unchecked")
			List<Cliente> outDatosCliente = (List<Cliente>) map.get("OUT_DATOS_CLIENTE");

			if (error == null && !outDatosCliente.isEmpty()) {
				datosCliente = outDatosCliente;
			}

		} catch (Exception o) {
			o.printStackTrace();
			logger.error("Error al obtener los datos (nombre y email): " + o.getStackTrace() + " mesage -> "
					+ o.getMessage() + " causa -> " + o.getCause());
		}

		return datosCliente;
	}

}
