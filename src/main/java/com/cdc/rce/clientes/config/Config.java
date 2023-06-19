package com.cdc.rce.clientes.config;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Config {
	private static final Logger LOGG = LogManager.getLogger("bloqueo");

	@Value("${jndi.name}")
	private String jndi;

	/**
	 * Devuelve la instancia de DataSource.
	 * 
	 * @return Instancia DataSource.
	 */
	@Bean
	@Qualifier("Standard")
	public DataSource dataSource() {
		LOGG.info("***** Mensaje dentro de DataSource *****");
		JndiObjectFactoryBean jndiObjectFactoryBean = new JndiObjectFactoryBean();
		try {
			jndiObjectFactoryBean.setJndiName(jndi);
			jndiObjectFactoryBean.setResourceRef(true);
			jndiObjectFactoryBean.setProxyInterface(DataSource.class);
			jndiObjectFactoryBean.afterPropertiesSet();

		} catch (Exception ex) {
			LOGG.error(ex);
		}
		return (DataSource) jndiObjectFactoryBean.getObject();
	}

	/**
	 * Devuelve la instancia de Template JDBC.
	 * 
	 * @return Instancia JdbcTemplace
	 */
	@Bean
	@Qualifier("Standard")
	public JdbcTemplate jdbcTemplate() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.setDataSource(dataSource());
		return jdbcTemplate;
	}
	
	 /**
     * Devuelve la instancia de RestTemplate.
     * @return Instancia RestTemplate.
     */
    @Bean
    @Qualifier("Standard")
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
