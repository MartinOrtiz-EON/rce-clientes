package com.cdc.rce.clientes.dao;

import com.cdc.rce.clientes.beans.Cliente;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface IClientesTramInconclusoDao {

    public List<Cliente> getDatosClienteConTramiteInconcluso();
}
