package com.cdc.rce.clientes.dao.impl;

import com.cdc.rce.clientes.beans.Cliente;
import com.cdc.rce.clientes.beans.GenericDao;
import com.cdc.rce.clientes.dao.IClientesTramInconclusoDao;
import com.cdc.rce.clientes.mappers.ClientesTramiteInconclusoMapper;
import oracle.jdbc.OracleTypes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class ClientesTramInconclusoDaoImpl extends GenericDao implements IClientesTramInconclusoDao {

    @Value("${db.schema}")
    private String SP_ID_SCHEMA;

    @Value("${db.tram.inc.package}")
    private String SP_ID_PACKAGE;

    @Value("${db.tram.inc.sp}")
    private String SP_ID_SP;
    @Override
    public List<Cliente> getDatosClienteConTramiteInconcluso(){
        List<Cliente> lstClientes = new ArrayList<>();

        try {
            SimpleJdbcCall jdbcCall = new SimpleJdbcCall(getDataSource())
                    .withSchemaName(SP_ID_SCHEMA)
                    .withCatalogName(SP_ID_PACKAGE)
                    .withProcedureName(SP_ID_SP)
                    .declareParameters(
                            new SqlOutParameter("P_MENSAJE_ERROR", OracleTypes.VARCHAR),
                            new SqlOutParameter("CUR_CONTACTOS", OracleTypes.CURSOR, new ClientesTramiteInconclusoMapper()));

            Map<String, Object> map = jdbcCall.execute();

            String error = (String) map.get("P_MENSAJE_ERROR");

            if(error == null) {
                @SuppressWarnings("unchecked")
                List<Cliente> outDatosCliente = (List<Cliente>) map.get("CUR_CONTACTOS");
                lstClientes = outDatosCliente;
            }else{
                logger.error("ERROR SP Call ");
                logger.error(error);
            }


        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error al obtener los datos (nombre y email): "  + " message -> "
                    + e.getMessage() + " causa -> " + e.getCause());
        }
        return lstClientes;
    }
}
