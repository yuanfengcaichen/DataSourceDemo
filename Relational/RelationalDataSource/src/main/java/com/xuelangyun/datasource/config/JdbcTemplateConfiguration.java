package com.xuelangyun.datasource.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/** @Author mochen.qy @Date 2022/2/11 10:55 @Description: */
@Configuration
public class JdbcTemplateConfiguration {
  @Bean(name = "jdbcTemplateDynamicDataSource")
  public JdbcTemplate jdbcTemplate(@Qualifier("dynamicRoutingDataSource") DataSource dataSource) {
    return new JdbcTemplate(dataSource);
  }

  @Bean(name = "jdbcTemplateMysql")
  public JdbcTemplate jdbcTemplateMysql(@Qualifier("dataSourceMysql") DataSource dataSource) {
    return new JdbcTemplate(dataSource);
  }

  //  @Bean(name = "jdbcTemplateMysqlProxy")
  //  public JdbcTemplate jdbcTemplateMysqlProxy(
  //      @Qualifier("dataSourceMysqlProxy") DataSource dataSource) {
  //    return new JdbcTemplate(dataSource);
  //  }

  @Bean(name = "jdbcTemplatePostgres")
  public JdbcTemplate jdbcTemplatePostgres(@Qualifier("dataSourcePostgres") DataSource dataSource) {
    return new JdbcTemplate(dataSource);
  }
}
