package app.conf;

import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;

/**
 * Created by msarcevic on 11/14/16.
 */
@Configuration
@ComponentScan("app")
@EnableJpaRepositories(basePackages =  {"app.dao.repository"})
public class SpringJpaConfig {

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            EntityManagerFactoryBuilder builder,
            DataSource datasource) {
        return builder
                .dataSource(datasource)
                .packages("app.model")
                .build();
    }
}
