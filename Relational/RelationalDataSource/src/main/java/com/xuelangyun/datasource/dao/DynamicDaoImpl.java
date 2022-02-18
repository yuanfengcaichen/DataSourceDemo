package com.xuelangyun.datasource.dao;

// import com.baomidou.dynamic.datasource.annotation.DS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/** @Author mochen.qy @Date 2022/2/14 11:18 @Description: */
// @Service
// @DS("mysql1")
public class DynamicDaoImpl {
  @Autowired private JdbcTemplate jdbcTemplate;

  public List selectAll() {
    return jdbcTemplate.queryForList("select * from food_info");
  }

  // @DS("mysql2")
  public List selectByCondition() {
    return jdbcTemplate.queryForList(
        "select * from food_info where create_datetime > '2021-07-15'");
  }

  public void addToDB() {
    addMysql1();
    addMysql2();
  }

  // @DS("mysql1")
  public void addMysql1() {
    jdbcTemplate.execute("insert into food_info (`type`,`mark`,`remarks`) values ('1','2','3')");
  }
  // @DS("mysql2")
  public void addMysql2() {
    jdbcTemplate.execute("insert into food_info (`type`,`mark`,`remarks`) values ('1','2','3')");
  }
}
