package com.cdc.rce.clientes.service;


public interface IEmailService {
	public boolean enviarEmail(String destinatario, String nombre, String asunto, String plantilla);
}
