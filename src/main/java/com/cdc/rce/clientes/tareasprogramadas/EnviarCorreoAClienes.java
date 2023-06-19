package com.cdc.rce.clientes.tareasprogramadas;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;

import com.cdc.rce.clientes.beans.Cliente;
import com.cdc.rce.clientes.service.IEmailService;

@Component
@EnableScheduling
public class EnviarCorreoAClienes {
	
	private String asunto = "Promociones RCE ";
	private String plantilla="rce-cliente.html";
	
	@Autowired
	private IEmailService emailService;
	
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
	
	
}
