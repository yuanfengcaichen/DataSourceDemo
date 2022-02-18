package com.xuelangyun;

import com.alibaba.druid.pool.DruidDataSource;
import com.xuelangyun.datasource.RelationalApplication;
import com.xuelangyun.datasource.config.DynamicRoutingDataSource;
import com.xuelangyun.datasource.dao.dao.FoodInfoService;
import com.xuelangyun.datasource.utils.SpringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/** @Author mochen.qy @Date 2022/2/11 10:54 @Description: */
@SpringBootTest(classes = RelationalApplication.class)
public class MysqlDataSourceTest {
  @Autowired JdbcTemplate jdbcTemplateMysql;
  //  @Autowired JdbcTemplate jdbcTemplateMysqlProxy;
  @Autowired JdbcTemplate jdbcTemplateDynamicDataSource;

  @Test
  public void selectByCondition() {
    List<Map<String, Object>> list =
        jdbcTemplateMysql.queryForList(
            "select * from space_flight_entry where entry_status = ? and kind = ? and status = ?",
            0,
            0,
            2);
    list.forEach(l -> System.out.println(l));
  }

  //  @Test
  //  public void selectByConditionByProxy() {
  //    List<Map<String, Object>> list =
  //        jdbcTemplateMysqlProxy.queryForList(
  //            "select * from space_flight_entry where entry_status = ? and kind = ? and status =
  // ?",
  //            0,
  //            0,
  //            2);
  //    list.forEach(l -> System.out.println(l));
  //  }

  @Test
  public void dynamicDataSource() {

    //    jdbcTemplateMysql.execute("insert into food_info (`type`,`mark`,`remarks`) values
    // ('1','2','3')");

    DruidDataSource druidDataSource = new DruidDataSource();
    druidDataSource.setUrl(
        "jdbc:mysql://localhost:3306/food_2?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=Asia/Shanghai");
    druidDataSource.setUsername("root");
    druidDataSource.setPassword("1qaz");
    // dbkey为数据源标识
    DynamicRoutingDataSource.dataSourcesMap.put("dbkey", druidDataSource);
    String[] beanNames = SpringUtils.getBeanNames(DataSource.class);
    DataSource dataSource = (DataSource) DynamicRoutingDataSource.dataSourcesMap.get("dbkey");
    DynamicRoutingDataSource.setDataSource("dbkey");
    jdbcTemplateDynamicDataSource.execute(
        "insert into food_info (`type`,`mark`,`remarks`) values ('1','2','3')");
  }

  @Autowired FoodInfoService foodInfoService;

  @Test
  public void testTransational() {
    foodInfoService.dynamicDataSource();
  }

  @Autowired ThreadPoolTaskExecutor taskExecutor;

  @Test
  public void ThreadTest() {
    CountDownLatch countDownLatch = new CountDownLatch(4);
    taskExecutor.submit(
        () -> {
          dynamicDataSource();
          countDownLatch.countDown();
        });
    taskExecutor.submit(
        () -> {
          dynamicDataSource();
          countDownLatch.countDown();
        });
    taskExecutor.submit(
        () -> {
          dynamicDataSource();
          countDownLatch.countDown();
        });
    taskExecutor.submit(
        () -> {
          dynamicDataSource();
          countDownLatch.countDown();
        });

    try {
      countDownLatch.await();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  private void tt() {
    System.out.println(Thread.currentThread().getName() + ": 运行开始");
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println(Thread.currentThread().getName() + ": 运行结束");
  }
}
