package com.xuelangyun.datasource.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/** @Author mochen.qy @Date 2022/2/11 10:41 @Description: */
@Configuration
public class DataSourceConfiguration {
  @Autowired ProxyFactory proxyFactory;
  @Bean
  @ConfigurationProperties("spring.datasource")
  public DataSource druidDataSource() {
    return new DruidDataSource();
  }

  @Primary
  @Bean("dataSourceProxy")
  public DataSource dataSource(DataSource druidDataSource) {
    Object proxy = proxyFactory.getProxy(druidDataSource);
    return (DataSource) proxy;
  }
}
