//package com.amis.misa.config;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.persistence.EntityManagerFactory;
//import javax.sql.DataSource;
//
//import org.springframework.beans.factory.annotation.Qualifier;
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
//@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories(transactionManagerRef = "mysqlTransactionManager", entityManagerFactoryRef = "mysqlEntityManagerFactory", basePackages = "com.amis.misa.repositories.app")
//public class AppDatasource {
//	@Bean(name = "mysqlDataSource")
//	@Primary
//	@ConfigurationProperties(prefix = "spring.datasource")
//	public DataSource dataSource() {
//		return DataSourceBuilder.create().build();
//	}
//
//	@Primary
//	@Bean(name = "mysqlEntityManagerFactory")
//	public LocalContainerEntityManagerFactoryBean entityEntityManagerFactory() {
//		LocalContainerEntityManagerFactoryBean  builder=new LocalContainerEntityManagerFactoryBean();
//		builder.setDataSource(dataSource());
//		builder.setPackagesToScan(new String[] {"com.amis.misa.entities.app"});
//		HibernateJpaVendorAdapter vendorAdapter= new HibernateJpaVendorAdapter();
//		builder.setJpaVendorAdapter(vendorAdapter);
//		Map<String, String> hibernateProperties = new HashMap<>();
//        hibernateProperties.put("hibernate.hbm2ddl.auto", "update");
//        hibernateProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
//        hibernateProperties.put("hibernate.show_sql","true");
//        hibernateProperties.put("hibernate.format_sql", "true");
//		builder.setJpaPropertyMap(hibernateProperties);
//		return builder;
//	}
//	@Primary
//	@Bean(name = "mysqlTransactionManager")
//	public PlatformTransactionManager entityTransactionManager(
//			@Qualifier("mysqlEntityManagerFactory") EntityManagerFactory mysqlEntityManagerFactory) {
//		return new JpaTransactionManager(mysqlEntityManagerFactory);
//	}
//}
