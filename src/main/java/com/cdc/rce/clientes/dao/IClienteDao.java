package com.cdc.rce.clientes.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdc.rce.clientes.beans.Cliente;

@Repository
public interface IClienteDao {
	public List<Cliente> getDatosCliente();

}
