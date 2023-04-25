package gmc.poc.jdbc.namedjdbctemplate.configurations;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;


@Configuration
public class DataSourceConfig {
	
	@Bean
	@ConfigurationProperties(value = "spring.datasource")
	public DataSourceProperties getDataSourceProperties() {
		return new DataSourceProperties();
	}
	
	@Bean
	public DataSource dataSource() {
		return getDataSourceProperties().initializeDataSourceBuilder().build();
	}
	
	@Bean
	public NamedParameterJdbcTemplate jdbcTemplate() {
		return new NamedParameterJdbcTemplate(dataSource());
	}

}
