package com.nanourl.config;

/*
 * Server configuration file for configuring with MySQL database
 * 
 * Author: Harsh Vasoya 
 */

import java.util.Properties;

import javax.persistence.EntityManagerFactory;

import org.hibernate.ejb.HibernatePersistence;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.nanourl.helpers.Constants;

@Configuration
@EnableWebMvc
@EnableAsync
@EnableScheduling
@ComponentScan("com.nanourl")
@EnableJpaRepositories(basePackages = { "com.nanourl" })
public class WebConfig extends WebMvcConfigurerAdapter {

	@Bean
	public EntityManagerFactory entityManagerFactory() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl(Constants.DB_URL);
		dataSource.setUsername(Constants.DB_USERNAME);
		dataSource.setPassword(Constants.DB_PASSWORD);

		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setJpaVendorAdapter(createJPAAdapter());
		factory.setDataSource(dataSource);
		factory.setPersistenceProviderClass(HibernatePersistence.class);
		factory.setPackagesToScan("com.nanourl");
		factory.setJpaProperties(createJPAProperties());
		factory.afterPropertiesSet();

		return factory.getObject();
	}

	@Bean
	public JpaTransactionManager transactionManager() throws Exception {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory());

		return transactionManager;
	}

	private JpaVendorAdapter createJPAAdapter() {
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setShowSql(true);
		vendorAdapter.setGenerateDdl(true);
		vendorAdapter.setDatabase(Database.MYSQL);
		return vendorAdapter;
	}

	private Properties createJPAProperties() {
		Properties properties = new Properties();
		return properties;
	}
}
