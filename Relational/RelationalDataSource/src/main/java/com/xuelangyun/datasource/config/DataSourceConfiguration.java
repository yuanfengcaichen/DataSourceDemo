package com.xuelangyun.datasource.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.beans.BeansException;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.Arrays;

/** @Author mochen.qy @Date 2022/2/11 10:41 @Description: */
@Configuration
public class DataSourceConfiguration implements ApplicationContextAware {
  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    //    DataSource bean = applicationContext.getBean(DataSource.class);
    String[] beanNamesForType = applicationContext.getBeanNamesForType(DataSource.class);
    System.out.println(Arrays.asList(beanNamesForType));
  }

  @Bean("dataSourceMysql")
  @ConfigurationProperties("spring.datasource.mysql")
  public DataSource druidDataSource() {
    return new DruidDataSource();
  }

  @Bean("dataSourcePostgres")
  @ConfigurationProperties("spring.datasource.postgres")
  public DataSource dataSourcePostgre() {
    return new DruidDataSource();
  }

  @Bean
  @ConfigurationProperties("spring.datasource")
  public DataSource defaultDataSource() {
    return DruidDataSourceBuilder.create().build();
  }

  @Bean("dynamicRoutingDataSource")
  @Primary
  @DependsOn({"springUtils"})
  public DynamicRoutingDataSource dataSource() {
    DynamicRoutingDataSource dynamicDataSource = new DynamicRoutingDataSource();
    dynamicDataSource.setTargetDataSources(DynamicRoutingDataSource.dataSourcesMap);
    return dynamicDataSource;
  }
}
