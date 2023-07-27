package com.cdc.rce.clientes.tareasprogramadas;

import java.util.ArrayList;
import java.util.List;

import com.cdc.rce.clientes.service.IClienteService;
import com.cdc.rce.clientes.service.IClientesTramIncService;

import org.apache.logging.log4j.LogManager;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.cdc.rce.clientes.beans.Cliente;
import com.cdc.rce.clientes.service.IEmailService;

@Component
@EnableScheduling
public class EnviarCorreoAClienes {

	private static final Logger logger = (Logger) LogManager.getLogger(EnviarCorreoAClienes.class);
	
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

	// Todos los dias a las 08:00 a.m.
	@Scheduled(cron = "${cron.clientes.rce}")
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
						logger.warn("Ocurrio un error al momento de mandar el email");
					}
				}
			}

		} catch (Exception e) {
			logger.warn("Ocurrio un error al momento de obtener la lista de clientes RCE");
		}

	}

	@Value("${email.tramite.inconcluso.asunto}")
	private String asuntoTramIncRCE;

	@Value("${email.tramite.inconcluso.plantilla}")
	private String plantillaTramIncRCE;

	@Scheduled(cron = "${cron.expression.notificar.tram.inc}")
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
						logger.warn("Ocurrio un error al momento de mandar el email");
						e.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
			logger.warn("Ocurrio un error al momento de obtener la lista de clientes RCE tramite inconcluso");
			e.printStackTrace();
		}
	}

}
