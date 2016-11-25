package app.conf;

import app.mybatis.mappers.SequencesMapper;
import app.mybatis.mappers.UserMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * Created by msarcevic on 11/16/16.
 */
@Configuration
public class MyBatisConfig {

    @Autowired
    DataSource dataSource;

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {

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
