package com.tao.player.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

//@Configuration
//@ComponentScan(basePackages = "com.tao.player", useDefaultFilters = true, excludeFilters = {
//		@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Controller.class) })
//@PropertySource(value = { "classpath:db.properties", "classpath:hibernate.properties" })
//@EnableWebMvc
//@EnableTransactionManagement
public class SpringConfig {
	@Value("${db.driverClass}")
	private String DRIVERCLASSNAME;

	@Value("${db.username}")
	private String USERNAME;

	@Value("${db.password}")
	private String PASSWORD;

	@Value("${db.jdbcUrl}")
	private String jdbcUrl;

	@Value("${db.initPoolSize}")
	private int INITPOOLSIZE;
	@Value("${db.maxPoolsize}")
	private int MAXPOOLSIZE;
	@Value("${db.MinIdle}")
	private int MINIDLE;
	@Value("${db.MaxActive}")
	private int MAXACTIVE;
	@Value("${db.MaxWait}")
	private int MAXWAIT;

	@Value("${hibernate.hbm2ddl.auto}")
	private String HBM2DDL_AUTO;

	@Value("${hibernate.show_sql}")
	private String SHOW_SQL;

	@Value("${hibernate.format_sql}")
	private String FORMAT_SQL;

	@Value("${hibernate.dialect}")
	private String DIALECT;

	@Bean(name = "dataSource")
	public DataSource dataSource() {
		System.out.println("DRIVERCLASSNAME="+DRIVERCLASSNAME);
		System.out.println("jdbcUrl="+jdbcUrl);
		System.out.println("USERNAME="+USERNAME);
		System.out.println("PASSWORD="+PASSWORD);
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(DRIVERCLASSNAME);
		dataSource.setUrl(jdbcUrl);
		dataSource.setUsername(USERNAME);
		dataSource.setPassword(PASSWORD);

		/* 配置初始化大小、最小、最大 */
		//dataSource.setInitialSize(INITPOOLSIZE);
		//dataSource.setMinIdle(MINIDLE);
		//dataSource.setMaxActive(MAXACTIVE);
		/* 配置获取连接等待超时的时间 */
		//dataSource.setMaxWait(MAXWAIT);
		/* 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒 */
		//dataSource.setTimeBetweenEvictionRunsMillis(60000);
		/* 配置一个连接在池中最小生存的时间，单位是毫秒 */
		//dataSource.setMinEvictableIdleTimeMillis(300000);
		/* 申请连接的时候检测，如果空闲时间大于timeBetweenEvictionRunsMillis，执行validationQuery检测连接是否有效 */
		//dataSource.setTestWhileIdle(true);
		//dataSource.setValidationQuery("select 1");
		return dataSource;
	}
	
	@Bean(name="sessionFactory")
	public LocalSessionFactoryBean  sessionFactory() {
		LocalSessionFactoryBean sessionFactory= new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan(new String[] {"com.tao.player.pojo"});
		Properties hibernateProperties = new Properties();
		hibernateProperties.put(Environment.DIALECT, DIALECT);
		hibernateProperties.put(Environment.SHOW_SQL, SHOW_SQL);
		hibernateProperties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
		hibernateProperties.put(Environment.HBM2DDL_AUTO, HBM2DDL_AUTO);
		hibernateProperties.put(Environment.FORMAT_SQL, FORMAT_SQL);
		sessionFactory.setHibernateProperties(hibernateProperties);
		return sessionFactory;
	}

//	@Bean(name = "sessionFactory")
//	public SessionFactory sessionFactory() {
//		SessionFactory sessionFactory = null;
//		try {
//			org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration();
//			// Hibernate settings equivalent to hibernate.cfg.xml's properties
//			Properties settings = new Properties();
//			settings.put(Environment.DRIVER, DRIVERCLASSNAME);
//			settings.put(Environment.URL, jdbcUrl);
//			settings.put(Environment.USER, USERNAME);
//			settings.put(Environment.PASS, PASSWORD);
//			settings.put(Environment.DIALECT, DIALECT);
//			settings.put(Environment.SHOW_SQL, SHOW_SQL);
//			settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
//			//settings.put(Environment.HBM2DDL_AUTO, HBM2DDL_AUTO);
//			settings.put(Environment.FORMAT_SQL, FORMAT_SQL);
//			configuration.setProperties(settings);
//			configuration.addAnnotatedClass(AuthUser.class);
//			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
//					.applySettings(configuration.getProperties()).build();
//			sessionFactory = configuration.buildSessionFactory(serviceRegistry);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return sessionFactory;
//	}
	
	@Bean(name="transactionManager")
	public TransactionManager transactionManager()	{
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory().getObject());
		return transactionManager;
	}

	public static void main(String[] args) {
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
		System.out.println(applicationContext.getBean("dataSource"));
		System.out.println(applicationContext.getBean("sessionFactory"));
		System.out.println(applicationContext.getBean("transactionManager"));
	}
}