package com.cdc.rce.clientes.controller;

import java.util.ArrayList;
import java.util.List;

import com.cdc.rce.clientes.service.IClientesTramIncService;
import com.cdc.rce.clientes.service.IEmailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdc.rce.clientes.beans.Cliente;
import com.cdc.rce.clientes.service.IClienteService;

@CrossOrigin
@RestController
public class ClienteController {
	
	@Autowired
	private IEmailService emailService;

	@Autowired
	private IClienteService clienteService;

	@Autowired
	private IClientesTramIncService clientesTramIncService;

	@Value("${email.asunto.clientes.rce}")
	private String asuntoClienteRCE;

	@Value("${email.plantilla.clientes.rce}")
	private String plantillaClienteRCE;

	
	@PostMapping("/listar")
	public ResponseEntity<List<Cliente>> listarCupones() {
		List<Cliente> datosCliente = clienteService.listarDatos();
		this.enviarCorreoClienteRCE();
		return new ResponseEntity<>(datosCliente, HttpStatus.OK);
	}


	@PostMapping("/lstTramInc")
	public ResponseEntity<List<Cliente>> listarClientes() {
		List<Cliente> datosCliente = clientesTramIncService.getClientesTramInc();
		this.enviarCorreoClientesTramIncRCE();
		return new ResponseEntity<>(datosCliente, HttpStatus.OK);
	}
	
	
	public void enviarCorreoClienteRCE() {

		try {
			// List<Cliente> datosCliente = clienteService.listarDatos();
			Cliente cliente1 = new Cliente("MARTIN ORTIZ DIONICIO", "martin.ortiz@eon.com.mx");
			Cliente cliente2 = new Cliente("RAFAEL ORTIZ GONZALEZ", "leo.martin178@gmail.com");
			List<Cliente> datosCliente = new ArrayList<Cliente>();

			datosCliente.add(cliente1);
			datosCliente.add(cliente2);

			if (datosCliente != null && !datosCliente.isEmpty()) {
				for (Cliente cliente : datosCliente) {
					try {
						emailService.enviarEmail(cliente.getEmail(), cliente.getNombre(), asuntoClienteRCE,
								plantillaClienteRCE);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Value("${email.tramite.inconcluso.asunto}")
	private String asuntoTramIncRCE;

	@Value("${email.tramite.inconcluso.plantilla}")
	private String plantillaTramIncRCE;

	
	public void enviarCorreoClientesTramIncRCE() {

		try {
			// List<Cliente> lstClientes = clientesTramIncService.getClientesTramInc();
			List<Cliente> lstClientes = new ArrayList<>();
			Cliente cliente1 = new Cliente("RAMON CRUZ DE LA CRUZ", "ramon.cruz@eon.com.mx");
			Cliente cliente2 = new Cliente("RAMON CRUZ", "ramon_cruz92@hotmail.com");

			lstClientes.add(cliente1);
			lstClientes.add(cliente2);

			if (lstClientes != null && !lstClientes.isEmpty()) {
				for (Cliente cliente : lstClientes) {
					try {
						emailService.enviarEmail(cliente.getEmail(), cliente.getNombre(), asuntoTramIncRCE,
								plantillaTramIncRCE);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
