package com.ectrip.ec.config;

import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.druid.pool.DruidDataSource;

@Configuration 
@EnableTransactionManagement
public class DruidDataSourceConfiguration implements EnvironmentAware{
	@Value("${spring.datasource.url}")
    private String dbUrl;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;
	
	@Value("${spring.jpa.hibernate.dialect}")  
    private String dialect;  
    @Value("${spring.jpa.format-sql}")  
    private String formatSql;  
    @Value("${spring.jpa.show-sql}")  
    private String showSql;
	private RelaxedPropertyResolver propertyResolver; 
	
	@Value("${spring.datasource.initialSize}")    
    private int initialSize;    
    @Value("${spring.datasource.minIdle}")    
    private int minIdle;    
    @Value("${spring.datasource.maxActive}")    
    private int maxActive;    
    @Value("${spring.datasource.maxWait}")    
    private int maxWait;    
    @Value("${spring.datasource.timeBetweenEvictionRunsMillis}")    
    private int timeBetweenEvictionRunsMillis;    
    @Value("${spring.datasource.minEvictableIdleTimeMillis}")    
    private int minEvictableIdleTimeMillis;    
    @Value("${spring.datasource.validationQuery}")    
    private String validationQuery;    
    @Value("${spring.datasource.testWhileIdle}")    
    private boolean testWhileIdle;    
    @Value("${spring.datasource.testOnBorrow}")    
    private boolean testOnBorrow;    
    @Value("${spring.datasource.testOnReturn}")    
    private boolean testOnReturn;    
    @Value("${spring.datasource.poolPreparedStatements}")    
    private boolean poolPreparedStatements;    
    @Value("${spring.datasource.maxPoolPreparedStatementPerConnectionSize}")    
    private int maxPoolPreparedStatementPerConnectionSize;    
    @Value("${spring.datasource.filters}")    
    private String filters;    
    @Value("${spring.datasource.connectionProperties}")    
    private String connectionProperties;    
    @Value("${spring.datasource.useGlobalDataSourceStat}")    
    private boolean useGlobalDataSourceStat;

	@Override  
	public void setEnvironment(Environment env) {  
	    this.propertyResolver = new RelaxedPropertyResolver(env, "spring.hibernate.");  
	}  

	@Bean
	public DataSource druidDataSource(){
	    DruidDataSource druidDataSource = new DruidDataSource ();
	    druidDataSource.setDriverClassName(driverClassName);
        druidDataSource.setUrl(dbUrl);
        druidDataSource.setUsername(username);
        druidDataSource.setPassword(password);
        druidDataSource.setInitialSize(initialSize);    
        druidDataSource.setMinIdle(minIdle);    
        druidDataSource.setMaxActive(maxActive);    
        druidDataSource.setMaxWait(maxWait);    
        druidDataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);    
        druidDataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);    
        druidDataSource.setValidationQuery(validationQuery);    
        druidDataSource.setTestWhileIdle(testWhileIdle);    
        druidDataSource.setTestOnBorrow(testOnBorrow);    
        druidDataSource.setTestOnReturn(testOnReturn);    
        druidDataSource.setPoolPreparedStatements(poolPreparedStatements);    
        druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize); 
        druidDataSource.setUseGlobalDataSourceStat(useGlobalDataSourceStat);
        try {
            druidDataSource.setFilters("stat, wall");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        druidDataSource.setConnectionProperties(connectionProperties);
	    return druidDataSource;
	}

	@Bean
	public LocalSessionFactoryBean sessionFactory(){
	    LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
	    sessionFactory.setDataSource (this.druidDataSource ());
	    sessionFactory.setPackagesToScan (new String[] {"com.ectrip.ec.model.*"});
	    Properties properties1 = new Properties();  
        properties1.setProperty("hibernate.dialect",dialect);  
        properties1.setProperty("hibernate.show_sql", showSql);  
        properties1.setProperty("hibernate.format_sql",formatSql);  
        sessionFactory.setHibernateProperties(properties1);
	    return sessionFactory;
	}

	@Bean
	public HibernateTemplate hibernateTemplate(){
	    HibernateTemplate temp = new HibernateTemplate();
	    temp.setSessionFactory (this.sessionFactory ().getObject ());
	    return temp;
	}

	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
	    HibernateTransactionManager txManager = new HibernateTransactionManager();
	    txManager.setSessionFactory(sessionFactory);
	    return txManager;
	 }


	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
	   return new PersistenceExceptionTranslationPostProcessor();
	}
}
