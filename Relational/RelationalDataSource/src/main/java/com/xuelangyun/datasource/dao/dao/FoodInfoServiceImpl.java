package com.xuelangyun.datasource.dao.dao;

import com.xuelangyun.datasource.annotation.XOTransactional;
import com.xuelangyun.datasource.config.DynamicRoutingDataSource;
import com.xuelangyun.datasource.utils.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.Arrays;

/** @Author mochen.qy @Date 2022/2/15 18:17 @Description: */
@XOTransactional
@Service
public class FoodInfoServiceImpl implements FoodInfoService {

  //  @Autowired JdbcTemplate jdbcTemplateMysql;
  @Autowired JdbcTemplate jdbcTemplateDynamicDataSource;

  @Override
  public void dynamicDataSource() {
    jdbcTemplateDynamicDataSource.execute(
        "insert into food_info (`type`,`mark`,`remarks`) values ('1','2','3')");

    String dataSourceName = "dbkey";

    DataSourceProperties dataSourceProperties = new DataSourceProperties();
    dataSourceProperties.setUrl(
        "jdbc:mysql://codeqi.club:3306/food?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=Asia/Shanghai");
    dataSourceProperties.setUsername("root");
    dataSourceProperties.setPassword("1qaz");
    SpringUtils.addDataSource(dataSourceName, dataSourceProperties);

    String[] beanNames = SpringUtils.getBeanNames(DataSource.class);
    System.out.println("当前数据源列表" + Arrays.asList(beanNames));

    DataSource druidDataSource = (DataSource) SpringUtils.getBean(dataSourceName);

    // dbkey为数据源标识
    DynamicRoutingDataSource.dataSourcesMap.put(dataSourceName, druidDataSource);
    DynamicRoutingDataSource.setDataSource("dbkey");
    jdbcTemplateDynamicDataSource.execute(
        "insert into food_info (`type`,`mark`,`remarks`) values ('1','2','3')");
    //    throw new RuntimeException("测试事务回滚");
  }
}
