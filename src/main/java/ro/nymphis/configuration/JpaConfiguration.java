package ro.nymphis.configuration;

import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(basePackages = { "ro.nymphis.repository"})
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
public class JpaConfiguration {

    @Value("${datasource.url}")
    private String dbURL;

    @Value("${datasource.username}")
    private String dbUsername;

    @Value("${datasource.password}")
    private String dbPassword;

    @Value("${hibernate.ddl-auto}")
    private String hibernateDDL;

    @Bean
    public DataSource dataSource() {
        final PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setURL(dbURL);
        dataSource.setUser(dbUsername);
        dataSource.setPassword(dbPassword);
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        final LocalContainerEntityManagerFactoryBean entityManagerFactoryBean =
                new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManagerFactoryBean.setPackagesToScan("ro.nymphis.model");
        entityManagerFactoryBean.setJpaProperties(properties());

        return entityManagerFactoryBean;
    }

    @Bean
    @Primary
    public PlatformTransactionManager transactionManager() {
        return new JpaTransactionManager();
    }

    private Properties properties() {
        final Properties hibernateProperties = new Properties();
        hibernateProperties.put(org.hibernate.cfg.Environment.HBM2DDL_AUTO, hibernateDDL);
        hibernateProperties.put(org.hibernate.cfg.Environment.SHOW_SQL, true);
        hibernateProperties.put(org.hibernate.cfg.Environment.FORMAT_SQL, true);
        hibernateProperties.put(org.hibernate.cfg.Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
        return hibernateProperties;
    }
}
