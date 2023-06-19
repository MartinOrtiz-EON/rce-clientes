package com.cdc.rce.clientes.tareasprogramadas;

import java.util.ArrayList;
import java.util.List;

import com.cdc.rce.clientes.service.IClientesTramIncService;
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
	
	private String asunto = "Promociones RCE ";
	private String plantilla="rce-cliente.html";
	
	@Autowired
	private IEmailService emailService;

	@Autowired
	private IClientesTramIncService clientesTramIncService;
	
	//Todos los dias a las 08:00 a.m.
	@Scheduled(cron = "0 15 11 * * *")
	public void enviarCorreoClienteRCE() {
		//List<Cliente> datosCliente = clienteService.listarDatos();
		
		try {
			Cliente cliente1 = new Cliente("MARTIN ORTIZ DIONICIO", "martin.ortiz@eon.com.mx");
			Cliente cliente2 = new Cliente("RAFAEL ORTIZ GONZALEZ", "leo.martin178@gmail.com");
			List<Cliente> datosCliente = new ArrayList<Cliente>();
			
			datosCliente.add(cliente1);
			datosCliente.add(cliente2);
			
			for (Cliente cliente : datosCliente) {
				try {
					
					emailService.enviarEmail(cliente.getEmail(), cliente.getNombre(),  asunto, plantilla);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}


	@Value("${email.tramite.inconcluso.asunto}")
	private String asuntoTramIncRCE;

	@Value("${email.tramite.inconcluso.plantilla}")
	private String plantillaTramIncRCE;

	@Scheduled(cron = "${cron.expression.notificar.tram.inc}")
	public void enviarCorreoClientesTramIncRCE() {

		try {
			//List<Cliente> lstClientes = clientesTramIncService.getClientesTramInc();
			List<Cliente> lstClientes = new ArrayList<>();
			Cliente cliente1 = new Cliente("RAMON CRUZ DE LA CRUZ", "ramon.cruz@eon.com.mx");
			Cliente cliente2 = new Cliente("RAMON CRUZ", "ramon_cruz92@hotmail.com");

			lstClientes.add(cliente1);
			lstClientes.add(cliente2);

			if(lstClientes != null && !lstClientes.isEmpty()) {
				for (Cliente cliente : lstClientes) {
					try {
						emailService.enviarEmail(cliente.getEmail(), cliente.getNombre(), asuntoTramIncRCE, plantillaTramIncRCE);
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
