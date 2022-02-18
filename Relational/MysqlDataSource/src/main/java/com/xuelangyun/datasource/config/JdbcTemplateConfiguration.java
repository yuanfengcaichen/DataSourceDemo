package com.xuelangyun.datasource.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/** @Author mochen.qy @Date 2022/2/11 10:55 @Description: */
@Configuration
public class JdbcTemplateConfiguration {
  @Bean(name = "jdbcTemplate")
  public JdbcTemplate jdbcTemplate(DataSource dataSource) {
    return new JdbcTemplate(dataSource);
  }
  @Bean(name = "jdbcTemplateProxy")
  public JdbcTemplate jdbcTemplateProxy(@Qualifier("dataSourceProxy") DataSource dataSource) {
    return new JdbcTemplate(dataSource);
  }
}
