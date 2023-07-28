//package com.amis.misa.config.AccountEmployeeDataSource;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.persistence.EntityManagerFactory;
//import javax.sql.DataSource;
//
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import com.zaxxer.hikari.HikariDataSource;
//
//@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories(transactionManagerRef = "accountTransactionManager", entityManagerFactoryRef = "accountEntityManagerFactory", basePackages = "com.amis.misa.repositories.account")
//public class AccountEmployeeDataSourceConfig {
//
//
//
//	
//    @ConfigurationProperties(prefix="spring.datasource.acc")
//    @Bean(name = "accountDataSource")
//	public DataSource dataSource() {
//		return DataSourceBuilder.create().build();
//	}
//	@Bean(name = "accountEntityManagerFactory")
//	@Qualifier
//	public LocalContainerEntityManagerFactoryBean accountEntityManagerFactory() {
//		LocalContainerEntityManagerFactoryBean  builder=new LocalContainerEntityManagerFactoryBean();
//		builder.setDataSource(dataSource());
//		builder.setPackagesToScan(new String[] {"com.amis.misa.entities.account"});
//		HibernateJpaVendorAdapter vendorAdapter= new HibernateJpaVendorAdapter();
//		builder.setJpaVendorAdapter(vendorAdapter);
//		Map<String, String> hibernateProperties = new HashMap<>();
//        hibernateProperties.put("hibernate.hbm2ddl.auto", "create-drop");
//        hibernateProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
//        hibernateProperties.put("hibernate.show_sql","true");
//        hibernateProperties.put("hibernate.format_sql", "true");
//		builder.setJpaPropertyMap(hibernateProperties);
//		
//		return builder;
//	}
//
//	@Bean(name = "accountTransactionManager")
//	@Qualifier
//	public PlatformTransactionManager accountTransactionManager(
//			@Qualifier("accountEntityManagerFactory") EntityManagerFactory accountEntityManagerFactory) {
//		return new JpaTransactionManager(accountEntityManagerFactory);
//	}
//}
