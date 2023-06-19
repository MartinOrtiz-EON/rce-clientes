package com.cdc.rce.clientes.service.impl;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import com.cdc.ModelosCore.beans.generic.GenericResponse;
import com.cdc.ModelosCore.beans.notificaciones.EmailCostumerRequest;
import com.cdc.ModelosCore.beans.notificaciones.NotificacionesResponse;
import com.cdc.ServiciosCore.templates.RestTemplateGeneric;

@Component
public class NotificacionesImpl extends RestTemplateGeneric {

	public GenericResponse<NotificacionesResponse> executeServiceNotificateService(String url, EmailCostumerRequest emailRequest) {

		HttpEntity<EmailCostumerRequest> entity = new HttpEntity<EmailCostumerRequest>(emailRequest);

		return request(url, entity, HttpMethod.POST, NotificacionesResponse.class);

	}
}
