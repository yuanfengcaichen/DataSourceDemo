package com.xuelangyun.datasource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/** @Author mochen.qy @Date 2022/2/11 10:12 @Description: */
@SpringBootApplication(
    scanBasePackages = "com.xuelangyun",
    exclude = DataSourceAutoConfiguration.class)
public class MysqlApplication {
  public static void main(String[] args) {
    SpringApplication.run(MysqlApplication.class, args);
  }
}
