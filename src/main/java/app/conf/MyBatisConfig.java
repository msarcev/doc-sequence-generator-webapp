package app.conf;

import app.mybatis.mappers.SequencesMapper;
import app.mybatis.mappers.UserMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * Created by msarcevic on 11/16/16.
 */
@Configuration
public class MyBatisConfig {

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder
                .create()
                .build();
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        DataSource dataSource = dataSource();

        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);

        SqlSessionFactory sessionFactory = sessionFactoryBean.getObject();
        sessionFactory.getConfiguration().addMapper(UserMapper.class);
        sessionFactory.getConfiguration().addMapper(SequencesMapper.class);

        return sessionFactory;
    }

    @Bean
    public UserMapper userMapper() throws Exception {
        SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());

        return sessionTemplate.getMapper(UserMapper.class);

    }

    @Bean
    public SequencesMapper sequencesMapper() throws Exception {
        SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());

        return sessionTemplate.getMapper(SequencesMapper.class);
    }
}
