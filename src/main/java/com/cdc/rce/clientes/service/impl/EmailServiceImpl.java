package com.cdc.rce.clientes.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.cdc.ModelosCore.beans.notificaciones.EmailCostumerRequest;
import com.cdc.rce.clientes.service.IEmailService;

@Service
public class EmailServiceImpl implements IEmailService {

	@Autowired
	private TemplateEngine templateEngine;

	@Autowired
	private NotificacionesImpl mailSender;

	@Value("${email.sender}")
	private String EMAIL_SENDER;
	@Value("${email.contact}")
	private String CONTACTO;
	@Value("${email.app}")
	private String APP;
	@Value("${email.id.alert}")
	private String EMAIL_ALERT;
	@Value("${email.url}")
	private String EMAIL_URL;
	
	private static final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);
	

	@Override
	public boolean enviarEmail(String destinatario, String nombre, String asunto, String plantilla) {

		try {

			Map<String, Object> propiedades = new HashMap<String, Object>();
			propiedades.put("nombre", nombre);
	
			EmailCostumerRequest requestEmail = new EmailCostumerRequest();

			Context context = new Context();
			context.setVariables(propiedades);
			String cuerpoString = templateEngine.process(plantilla, context);

			requestEmail.setTo(destinatario);
			requestEmail.setFrom(EMAIL_SENDER);
			requestEmail.setSubject(asunto);
			requestEmail.setBody(cuerpoString);
			requestEmail.setContacto(CONTACTO);
			requestEmail.setApp(APP);
			requestEmail.setIdAlerta(EMAIL_ALERT);

			logger.info(":::::: [CorreoService] - Envío de correo a {} con asunto '{}' y utilizando la plantilla '{}'",
					destinatario, asunto, plantilla);

			mailSender.executeServiceNotificateService(EMAIL_URL, requestEmail);
			return true;

		} catch (MailException e) {
			
			  logger.error(
			  ":::::: [CorreoService] - Hubo un error al enviar el correo a {} - Plantilla usada: {} - Mensaje de error: {}"
			  , destinatario, plantilla, e.getMessage());
			 
			e.printStackTrace();
		}
		return false;
	}

}
