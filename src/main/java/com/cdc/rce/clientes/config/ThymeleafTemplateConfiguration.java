package com.cdc.rce.clientes.config;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

/**
 * @author JoseGpeLeon
 * Establece la configuración personalizada para los Bean de Template Thymeleaf requeridos para
 * las plantillas de notificaciones.
 */
@Configuration
@EnableWebMvc
@ComponentScan(
basePackages = {"com.cdc.rce.clientes"}
)
public class ThymeleafTemplateConfiguration {

	/**
	 * Constructor por defecto y vacío.
	 */
	public ThymeleafTemplateConfiguration() {
	}

	/**
	 * Devuelve instancia de SpringTemplateEngine.
	 * @return Instancia SpringTemplateEngine.
	 */
	@Bean
	public SpringTemplateEngine templateEngine() {
	    SpringTemplateEngine templateEngine = new SpringTemplateEngine();
	    templateEngine.setTemplateResolver(this.thymeleafTemplateResolver());
	    return templateEngine;
	}

	/**
	 * Devuelve la instancia de SpringResourceTemplateResolver.
	 * @return Instancia SpringResourceTemplateResolver.
	 */
	@Bean
	public SpringResourceTemplateResolver thymeleafTemplateResolver() {
	    SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
	    templateResolver.setPrefix("/WEB-INF/views/");
	    templateResolver.setSuffix(".html");
	    templateResolver.setTemplateMode("HTML5");
	    return templateResolver;
	}

	/**
	 * Devuelve la instancia de ThymeleafViewResolver.
	 * @return Instancia ThymeleafViewResolver.
	 */
	@Bean
	public ThymeleafViewResolver thymeleafViewResolver() {
	    ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
	    viewResolver.setTemplateEngine(this.templateEngine());
	    return viewResolver;
	}
	
}
