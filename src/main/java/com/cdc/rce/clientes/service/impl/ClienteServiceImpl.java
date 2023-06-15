package com.cdc.rce.clientes.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdc.rce.clientes.beans.Cliente;
import com.cdc.rce.clientes.dao.IClienteDao;
import com.cdc.rce.clientes.service.IClienteService;

@Service
public class ClienteServiceImpl implements IClienteService {

	@Autowired
	private IClienteDao clienteDao;
	
	@Override
	public List<Cliente> listarDatos() {
		return clienteDao.getDatosCliente();
	}

}
