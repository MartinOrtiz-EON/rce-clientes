package com.cdc.rce.clientes.controller;

import java.util.List;

import com.cdc.rce.clientes.service.IClientesTramIncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdc.rce.clientes.beans.Cliente;
import com.cdc.rce.clientes.service.IClienteService;

@CrossOrigin
@RestController
public class ClienteController {
	
	@Autowired
	private IClienteService clienteService;

	@Autowired
	private IClientesTramIncService clientesTramIncService;
	
	@PostMapping("/listar")
	public ResponseEntity<List<Cliente>> listarCupones() {
		List<Cliente> datosCliente = clienteService.listarDatos();
		return new ResponseEntity<>(datosCliente, HttpStatus.OK);
	}


	@PostMapping("/lstTramInc")
	public ResponseEntity<List<Cliente>> listarClientes() {
		List<Cliente> datosCliente = clientesTramIncService.getClientesTramInc();
		return new ResponseEntity<>(datosCliente, HttpStatus.OK);
	}
}
