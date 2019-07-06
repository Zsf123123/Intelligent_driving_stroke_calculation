package com.muheda.dao.mysql.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * 数据源配置
 * @Author wangchong
 * @Date 2018/11/05 14:57
 * @Modify
 */
@Configuration
@MapperScan(basePackages = "com.muheda.dao.mysql.mapper", sqlSessionTemplateRef  = "sqlSessionTemplateEleCar")
public class ElectricCarDataSource {

	@Bean(name = "dataSourceEleCar")
    @ConfigurationProperties(prefix = "spring.datasource.elecar")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "sqlSessionFactoryEleCar")
    public SqlSessionFactory jr2SqlSessionFactory(@Qualifier("dataSourceEleCar") DataSource jtsEleCarDataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(jtsEleCarDataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/*.xml"));
        return bean.getObject();
    }

    
    @Bean(name = "transactionManagerEleCar")
    public DataSourceTransactionManager qtlrTransactionManager(@Qualifier("dataSourceEleCar") DataSource jtsEleCarDataSource) {
        return new DataSourceTransactionManager(jtsEleCarDataSource);
    }

    @Bean(name = "sqlSessionTemplateEleCar")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactoryEleCar") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
