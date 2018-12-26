package com.hxiloj.configuration;

import java.util.Properties;

import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jndi.JndiTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * JNDI configuration.
 * @author henry 
 */


@Configuration
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
@ComponentScan("com.hxiloj.configuration")
@EnableJpaRepositories(basePackages = "com.hxiloj.repository")
public class JNDIConfig {
	
	@Autowired
    private Environment env;
 
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() 
      throws NamingException {
        LocalContainerEntityManagerFactoryBean em 
          = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan(new String[] { "com.hxiloj.model" });

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
	    em.setJpaVendorAdapter(vendorAdapter);
	    em.setJpaProperties(hibernateProperties());
	    
        return em;
    }
 
    @Bean
    public DataSource dataSource() throws NamingException {
        return (DataSource) new JndiTemplate().lookup(env.getProperty("jdbc.url"));
    }
 
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }
    
    private Properties hibernateProperties() {
    	Properties properties = new Properties();
    	properties.put("hibernate.dialect", env.getRequiredProperty("hibernate.dialect"));
    	properties.put("hibernate.show_sql", env.getRequiredProperty("hibernate.show_sql"));
    	properties.put("hibernate.format_sql", env.getRequiredProperty("hibernate.format_sql"));
    	return properties;        
    }
}
