package com.niit.pistona;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@ComponentScan(basePackages="com.niit.pistona")
@EnableTransactionManagement
public class Config {

	@Bean(name="mydatasource")
	DataSource m1()
	{
		DriverManagerDataSource ds=new DriverManagerDataSource();
		ds.setDriverClassName("org.h2.Driver");
		ds.setUrl("jdbc:h2:~/pistona");
		ds.setUsername("sa");
		ds.setPassword("pistona");		
		return ds;
	}
	
	@Bean(name="myprop")
	Properties m2()
	{
		Properties p=new Properties();
		p.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		p.setProperty("hibernate.show_sql", "true");
		p.setProperty("hibernate.hbm2ddl.auto","update");	
		return p;
		
	}
	
	@Autowired
	@Bean(name="sf")
	LocalSessionFactoryBean m3(DataSource mydatasource,Properties myprop)
	{
	
		LocalSessionFactoryBean sf=new LocalSessionFactoryBean();
		sf.setDataSource(mydatasource);
		sf.setHibernateProperties(myprop);
		sf.setPackagesToScan("com.niit.pistona.Model");
		return sf;
	}
	
	@Bean(name="mytrans")
	@Autowired
	HibernateTransactionManager m4(SessionFactory sf)
	{
		HibernateTransactionManager htm=new HibernateTransactionManager();
		htm.setSessionFactory(sf);
		return htm;
	}

}
