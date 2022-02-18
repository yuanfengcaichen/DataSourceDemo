package com.xuelangyun;

import com.xuelangyun.datasource.MysqlApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

/** @Author mochen.qy @Date 2022/2/11 10:54 @Description: */
@SpringBootTest(classes = MysqlApplication.class)
public class MysqlDataSourceTest {
  @Autowired JdbcTemplate jdbcTemplateProxy;

  @Test
  public void selectByParam() {
    List<Map<String, Object>> list =
        jdbcTemplateProxy.queryForList(
            "select * from space_flight_entry where entry_status = ? and kind = ? and status = ?",
            0,
            0,
            2);
    list.forEach(
        l -> {
          System.out.println(l);
        });
  }
}
